package fjnu.edu.cn.xjsscttjh.utils;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fjnu.edu.cn.xjsscttjh.bean.ForecastInfo;
import fjnu.edu.cn.xjsscttjh.bean.TrendInfo;

/**
 * Created by GaoFei on 2018/1/28.
 * 彩票数据获取器
 */

public class LottyDataGetUtils {
    private static final String TAG = "LottyDataGetUtils";
    private LottyDataGetUtils(){

    }

    /**
     * 从发彩网抓取走势消息
     * @return
     */
    public static List<TrendInfo> getAllTrendInfoByFC(){
        List<TrendInfo> trendInfoList = new ArrayList<>();
        try{
            Document document = Jsoup.connect("http://www.es123.com/trends/").get();
            Element fcElement = document.getElementById("fucai_trend1l");
            Elements trendElements = fcElement.getElementsByClass("fucai_lottery_list clearfix");
            for(Element itemElement : trendElements){
                TrendInfo trendInfo = new TrendInfo();
                Elements logoElements = itemElement.getElementsByClass("fc_list_logo");
                String imgUrl = logoElements.get(0).getElementsByTag("img").get(0).attr("src");
                String name = logoElements.get(0).getElementsByTag("span").get(0).text();
                Elements liElements = itemElement.getElementsByClass("clearfix").get(0).getElementsByTag("li");
                Map<String, String> liMap = new LinkedHashMap<>();
                for(Element itemLiElemnt : liElements){
                    String trendName = itemLiElemnt.getElementsByTag("a").get(0).text();
                    String trendUrl = itemElement.getElementsByTag("a").attr("href");
                    liMap.put(trendName, trendUrl);
                }
                trendInfo.setImgUrl(imgUrl);
                trendInfo.setName(name);
                trendInfo.setTrendUrl(liMap);
                trendInfoList.add(trendInfo);
            }


            Element tcElement = document.getElementsByClass("fucai_trend").get(1);
            trendElements = tcElement.getElementsByClass("fucai_lottery_list clearfix");
            for(Element itemElement : trendElements){
                TrendInfo trendInfo = new TrendInfo();
                Elements logoElements = itemElement.getElementsByClass("fc_list_logo");
                String imgUrl = logoElements.get(0).getElementsByTag("img").get(0).attr("src");
                String name = logoElements.get(0).getElementsByTag("span").get(0).text();
                Elements liElements = itemElement.getElementsByClass("clearfix").get(0).getElementsByTag("li");
                Map<String, String> liMap = new LinkedHashMap<>();
                for(Element itemLiElemnt : liElements){
                    String trendName = itemLiElemnt.getElementsByTag("a").get(0).text();
                    String trendUrl = itemElement.getElementsByTag("a").attr("href");
                    liMap.put(trendName, trendUrl);
                }
                trendInfo.setImgUrl(imgUrl);
                trendInfo.setName(name);
                trendInfo.setTrendUrl(liMap);
                trendInfoList.add(trendInfo);
            }

        }catch (Exception e){
            e.printStackTrace();
            //no handle
            Log.i(TAG, "getAllTrendInfoByFC->exception:" + e);
        }
        return  trendInfoList;
    }

    /**
     * 从发彩网抓取预测消息
     * @return
     */
    public static Map<String, List<ForecastInfo>> getAllForecastInfoByFC(){
        Map<String, List<ForecastInfo>> forecastMap = new LinkedHashMap<>();
        try{
            Document document = Jsoup.connect("http://www.es123.com/").get();
            Elements forcaestElements = document.getElementsByClass("fuli_xinwen");
            for(Element itemElement : forcaestElements){
                Elements titleElements = itemElement.children().get(0).children().get(1).children();
                //Log.i(TAG, "child0->html:" + itemElement.children().get(0).html());
                Elements currAllElements = itemElement.children();
                //int index = 1;
                for(int i = 0; i < titleElements.size(); ++i){
                    Element itemTitleElement = titleElements.get(i);
                    String lottyTitle = itemTitleElement.text();
                    Log.i(TAG, "getAllForecastInfoByFC->lottyTitle:" + lottyTitle);
                    List<ForecastInfo> itemForecastInfos = new ArrayList<>();
                    Elements liElements = currAllElements.get(i + 1).children().get(1).children();
                    for(Element itemLiElement : liElements){
                        //Log.i(TAG, "itemLiElement->html:" + itemLiElement.html());
                        ForecastInfo forecastInfo = new ForecastInfo();
                        String url = itemLiElement.getElementsByTag("a").get(0).attr("href");
                        Log.i(TAG, "getAllForecastInfoByFC->url:" + url);
                        String time = itemLiElement.getElementsByTag("time").get(0).text();
                        String title = itemLiElement.getElementsByTag("a").get(0).text();
                        forecastInfo.setTime(time);
                        forecastInfo.setTitle(title);
                        forecastInfo.setUrl(url);
                        itemForecastInfos.add(forecastInfo);
                    }
                    forecastMap.put(lottyTitle, itemForecastInfos);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            //no handle
            Log.i(TAG, "getAllTrendInfoByFC->exception:" + e);
        }
        return forecastMap;
    }

    public static String getTrendChardHtmlByFc(String url){
        try{
            Document document = Jsoup.connect(url).get();
            Elements allElements = document.body().children();
            for(Element itemElement : allElements){
                Log.i(TAG, "getTrendChardHtmlByFc->id:" + itemElement.id());
                if(!"show".equals(itemElement.id())){
                    itemElement.attr("style", "display: none");
                }else{
                    Elements liElements =  itemElement.children().get(0).children().get(0).getElementsByTag("tr");
                    liElements.get(liElements.size() - 1).attr("style", "display: none");
                }
            }
            return document.outerHtml();
            //return document.outerHtml();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
