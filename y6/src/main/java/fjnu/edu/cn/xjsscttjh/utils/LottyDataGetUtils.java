package fjnu.edu.cn.xjsscttjh.utils;

import android.text.TextUtils;
import android.util.Log;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import fjnu.edu.cn.xjsscttjh.bean.ColorInfo;
import fjnu.edu.cn.xjsscttjh.bean.ForecastInfo;
import fjnu.edu.cn.xjsscttjh.bean.HistoryOpenInfo;
import fjnu.edu.cn.xjsscttjh.bean.NowOpenInfo;
import fjnu.edu.cn.xjsscttjh.bean.TrendInfo;
import fjnu.edu.cn.xjsscttjh.data.ConstData;

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
                if(!"重庆时时彩".equals(name))
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
                if(!"重庆时时彩".equals(name))
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
                    if("重庆时时彩".equals(lottyTitle))
                        continue;
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
                    Elements trElements =  itemElement.getElementsByTag("tr");
                    trElements.get(trElements.size() - 1).attr("style", "display: none");
                }
            }
            return document.outerHtml();
            //return document.outerHtml();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从发彩网抓取当前获奖的彩票信息
     * @return
     */
    public static List<NowOpenInfo> getNowOpenInfosByFc(){
        List<NowOpenInfo> infos = new ArrayList<>();
        try {
            Document document = Jsoup.connect("http://www.es123.com/draw_notice/").get();
            Elements trElements = document.body().getElementsByClass("kj_table_con").get(0).child(0).child(0).children();
            int trSize =  trElements.size();
            for(int i = 1; i < trSize; ++i){
                Element itemTrElement = trElements.get(i);
                String title = itemTrElement.child(0).text();
                String no = itemTrElement.child(1).text();
                String openDate = itemTrElement.child(2).text();
                StringBuilder builder = new StringBuilder();
                Elements liElements = itemTrElement.child(3).child(0).child(0).children();
                for(Element itemLiElement : liElements){
                    builder.append(" ").append(itemLiElement.text());
                }
                String number = builder.toString();
                number = number.trim();
                String head = itemTrElement.child(4).text();
                String tip = itemTrElement.child(7).text();
                NowOpenInfo info = new NowOpenInfo(title, no, openDate, number, head, tip);
                if(!"重庆时时彩".equals(info.getTitle()))
                    infos.add(info);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        for(NowOpenInfo info : infos){
            info.setImgUrl(ConstData.FC_LOTTY_IMG_URLS.get(info.getTitle()));
        }
        return infos;
    }

    /**
     * 从发彩网抓取历史开奖信息
     * @return
     */
    public static Map<String, List<ColorInfo>> getHistoryOpenInfoByFc(){
        Map<String, List<ColorInfo>> infoMap = new LinkedHashMap<>();
        Map<String, String> urls = new LinkedHashMap<>();
        urls.put("双色球", "http://www.es123.com/history/ssq/");
        urls.put("大乐透", "http://www.es123.com/history/dlt/");
        urls.put("福彩3D", "http://www.es123.com/history/fc3d/");
        urls.put("重庆时时彩", "http://www.es123.com/ssc/");
        urls.put("七星彩", "http://www.es123.com/history/qxc/");
        urls.put("七乐彩", "http://www.es123.com/history/qlc/");
        urls.put("排列3", "http://www.es123.com/history/pl3/");
        urls.put("排列5", "http://www.es123.com/history/pl5/");
        urls.put("15选5", "http://www.es123.com/history/l15x5/");
        Set<String> titles = urls.keySet();
        for(String title :titles){
            Log.i(TAG, "getHistoryOpenInfoByFc->title:" + title);
            String url = urls.get(title);
            if(title.equals("重庆时时彩")){
                //重庆时时彩页面特俗
                try{
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                    String openDate = sf.format(new Date());
                    //动态网页数据的抓取调用服务器
                    Connection connection = Jsoup.connect("http://120.24.18.183:8080/DyParseWebService/servlet/DyParseServlet");
                    connection.data("url", url);
                    Document sscDocument =   connection.post();
                    Elements sscTrElements = sscDocument.body().getElementById("draw_result").children();
                    int sscTrSize = sscTrElements.size();
                    Log.i(TAG, "sscTrSize:" + sscTrSize);
                    List<ColorInfo> sscInfos = new ArrayList<>();
                    for(int i = 0; i < sscTrSize; i++){
                        Elements sscTdElements = sscTrElements.get(i).children();
                        int sscTdSize = sscTdElements.size();
                        for(int k = 0; k < sscTdSize; k += 2){
                            ColorInfo sscInfo = new ColorInfo();
                            String sscNo = sscTdElements.get(k).text();
                            if(TextUtils.isEmpty(sscNo))
                                break;
                            sscInfo.setIssueNo(sscNo + "期");
                            Elements  strongElements = sscTdElements.get(k+1).children();
                            StringBuilder sscBuilder = new StringBuilder();
                            for(Element strongElement : strongElements){
                                sscBuilder.append(" ").append(strongElement.text());
                            }
                            sscInfo.setNumber(sscBuilder.toString().trim());
                            sscInfo.setOpenDate(openDate);
                            sscInfos.add(sscInfo);

                        }
                    }
                    Collections.sort(sscInfos, new Comparator<ColorInfo>() {
                        @Override
                        public int compare(ColorInfo o1, ColorInfo o2) {
                            return o2.getIssueNo().compareTo(o1.getIssueNo());
                        }
                    });
                    infoMap.put(title, sscInfos);

                }catch (Exception e){
                    e.printStackTrace();
                }

                continue;
            }
            try{
                Document document = Jsoup.connect(url).get();
                Elements trElements = document.body().getElementsByClass("lt_kaijiang_table").get(0).child(0).child(0).children();
                int trSize = trElements.size();
                List<ColorInfo> colorInfos = new ArrayList<>();
                for(int i = 1; i < trSize; ++i){
                    Element trElement = trElements.get(i);
                    ColorInfo colorInfo = new ColorInfo();
                    colorInfo.setIssueNo(trElement.child(0).text());
                    colorInfo.setOpenDate(trElement.child(1).text());
                    Elements liElements = trElement.child(2).child(0).child(0).children();
                    StringBuilder builder = new StringBuilder();
                    for(Element liElement : liElements){
                        builder.append(" ").append(liElement.text());
                    }
                    colorInfo.setNumber(builder.toString().trim());
                    colorInfos.add(colorInfo);
                }
                infoMap.put(title, colorInfos);
            }catch (Exception e){
                e.printStackTrace();
            }


        }
        return infoMap;
    }

    public static String getForcaestInfoByFc(String url){
        try{
            Document document = Jsoup.connect(url).get();
            return document.body().getElementsByClass("zx_seq_con_left").get(0).getElementsByTag("p").outerHtml();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取发彩网的历史开奖信息
     * @param title
     * @param url
     * @return
     */
    public static List<ColorInfo> getFcHistoryOpenInfos(String title, String url){
        if(title.equals("重庆时时彩")){
            //重庆时时彩页面特俗
            try{
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                String openDate = sf.format(new Date());
                //动态网页数据的抓取调用服务器
                Connection connection = Jsoup.connect("http://120.24.18.183:8080/DyParseWebService/servlet/DyParseServlet");
                connection.data("url", url);
                Document sscDocument =   connection.post();
                Elements sscTrElements = sscDocument.body().getElementById("draw_result").children();
                int sscTrSize = sscTrElements.size();
                Log.i(TAG, "sscTrSize:" + sscTrSize);
                List<ColorInfo> sscInfos = new ArrayList<>();
                for(int i = 0; i < sscTrSize; i++){
                    Elements sscTdElements = sscTrElements.get(i).children();
                    int sscTdSize = sscTdElements.size();
                    for(int k = 0; k < sscTdSize; k += 2){
                        ColorInfo sscInfo = new ColorInfo();
                        String sscNo = sscTdElements.get(k).text();
                        if(TextUtils.isEmpty(sscNo))
                            break;
                        sscInfo.setIssueNo(sscNo + "期");
                        Elements  strongElements = sscTdElements.get(k+1).children();
                        StringBuilder sscBuilder = new StringBuilder();
                        for(Element strongElement : strongElements){
                            sscBuilder.append(" ").append(strongElement.text());
                        }
                        sscInfo.setNumber(sscBuilder.toString().trim());
                        sscInfo.setOpenDate(openDate);
                        sscInfos.add(sscInfo);

                    }
                }
                Collections.sort(sscInfos, new Comparator<ColorInfo>() {
                    @Override
                    public int compare(ColorInfo o1, ColorInfo o2) {
                        return o2.getIssueNo().compareTo(o1.getIssueNo());
                    }
                });
                return sscInfos;

            }catch (Exception e){
                e.printStackTrace();
            }

        }else{
            try{
                Document document = Jsoup.connect(url).get();
                Elements trElements = document.body().getElementsByClass("lt_kaijiang_table").get(0).child(0).child(0).children();
                int trSize = trElements.size();
                List<ColorInfo> colorInfos = new ArrayList<>();
                for(int i = 1; i < trSize; ++i){
                    Element trElement = trElements.get(i);
                    ColorInfo colorInfo = new ColorInfo();
                    colorInfo.setIssueNo(trElement.child(0).text());
                    colorInfo.setOpenDate(trElement.child(1).text());
                    Elements liElements = trElement.child(2).child(0).child(0).children();
                    StringBuilder builder = new StringBuilder();
                    for(Element liElement : liElements){
                        builder.append(" ").append(liElement.text());
                    }
                    colorInfo.setNumber(builder.toString().trim());
                    colorInfos.add(colorInfo);
                }
                return colorInfos;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return new ArrayList<>();

    }
}
