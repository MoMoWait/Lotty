package cn.edu.fjnu.realtimecolor.model;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import cn.edu.fjnu.realtimecolor.data.ConstData;
import cn.edu.fjnu.realtimecolor.pojo.AllColorInfo;
import cn.edu.fjnu.realtimecolor.pojo.ColorInfo;

/**
 * Created by Administrator on 2017\9\5 0005.
 * 开奖信息获取,加载
 */

public class ColorInfoLoadTask extends AsyncTask<String, Integer, Integer> {

    public interface Callback{
        void onGetResult(List<AllColorInfo> allColorInfos);
    }

    private List<AllColorInfo> mAllColorInfos = new ArrayList<>();
    private Callback mCallback;

    public ColorInfoLoadTask(Callback callback){
        mCallback = callback;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        List<ColorInfo> oneColorList = new ArrayList<>();
        List<ColorInfo> towColorList = new ArrayList<>();
        List<ColorInfo> threeColorList = new ArrayList<>();
        //请求HTTP数据
        try{
            URL url = new URL(ConstData.ONE_COLOR_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            byte data[] = new byte[2048];
            InputStream stream = connection.getInputStream();
            StringBuilder builder = new StringBuilder();
            int readLength = stream.read(data);
            while(readLength > 0){
                builder.append( new String(data, 0 , readLength, Charset.forName("UTF-8")));
                readLength = stream.read(data);

            }
            stream.close();
            String headContent = builder.toString();
            JSONObject headerObject = new JSONObject(headContent);
            JSONArray headerArray =  headerObject.getJSONObject("result").getJSONArray("list");
            for(int i = 0; i < headerArray.length(); ++i){
                ColorInfo colorInfo = new ColorInfo();
                JSONObject itemObject = headerArray.getJSONObject(i);
                colorInfo.setOpenDate(itemObject.getString("opendate"));
                colorInfo.setIssueNo(itemObject.getString("issueno"));
                colorInfo.setNumber(itemObject.getString("number"));
                oneColorList.add(colorInfo);
            }

            url = new URL(ConstData.TWO_COLOR_URL);
            connection = (HttpURLConnection) url.openConnection();
            data = new byte[2048];
            stream = connection.getInputStream();
            builder = new StringBuilder();
            readLength = stream.read(data);
            while(readLength > 0){
                builder.append( new String(data, 0 , readLength, Charset.forName("UTF-8")));
                readLength = stream.read(data);
            }
            stream.close();
            headContent = builder.toString();
            headerObject = new JSONObject(headContent);
            headerArray =  headerObject.getJSONObject("result").getJSONArray("list");
            for(int i = 0; i < headerArray.length(); ++i){
                ColorInfo colorInfo = new ColorInfo();
                JSONObject itemObject = headerArray.getJSONObject(i);
                colorInfo.setOpenDate(itemObject.getString("opendate"));
                colorInfo.setIssueNo(itemObject.getString("issueno"));
                colorInfo.setNumber(itemObject.getString("number"));
                towColorList.add(colorInfo);
            }


            url = new URL(ConstData.THREE_COLOR_URL);
            connection = (HttpURLConnection) url.openConnection();
            data = new byte[2048];
            stream = connection.getInputStream();
            builder = new StringBuilder();
            readLength = stream.read(data);
            while(readLength > 0){
                builder.append( new String(data, 0 , readLength, Charset.forName("UTF-8")));
                readLength = stream.read(data);
            }
            stream.close();
            headContent = builder.toString();
            headerObject = new JSONObject(headContent);
            headerArray =  headerObject.getJSONObject("result").getJSONArray("list");
            for(int i = 0; i < headerArray.length(); ++i){
                ColorInfo colorInfo = new ColorInfo();
                JSONObject itemObject = headerArray.getJSONObject(i);
                colorInfo.setOpenDate(itemObject.getString("opendate"));
                colorInfo.setIssueNo(itemObject.getString("issueno"));
                colorInfo.setNumber(itemObject.getString("number"));
                threeColorList.add(colorInfo);
            }

            int oneColorLength = oneColorList.size();
            int twoColorLength = towColorList.size();
            int threeColorLength = threeColorList.size();
            int maxColorLenght = oneColorLength;
            if(maxColorLenght < twoColorLength)
                maxColorLenght = twoColorLength;
            else if(maxColorLenght < threeColorLength)
                maxColorLenght = threeColorLength;
            for(int i = 0; i < maxColorLenght; ++i){
                AllColorInfo allColorInfo = new AllColorInfo();
                if(i < oneColorLength)
                    allColorInfo.setOneColor(oneColorList.get(i));
                if(i < twoColorLength)
                    allColorInfo.setTwoColor(towColorList.get(i));
                if(i < threeColorLength)
                    allColorInfo.setThreeColor(threeColorList.get(i));
                mAllColorInfos.add(allColorInfo);
            }

        }catch (Exception e){

        }
        return null;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        mCallback.onGetResult(mAllColorInfos);
    }
}
