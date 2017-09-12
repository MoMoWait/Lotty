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
import cn.edu.fjnu.realtimecolor.pojo.HotInformation;

/**
 * Created by Administrator on 2017\9\3 0003.
 * 热门信息加载
 */

public class InformationLoadTask extends AsyncTask<String, Integer, Integer> {

    public interface Callback{
        void onGetResult(List<HotInformation> informations);
    }

    private Callback mCallback;
    private List<HotInformation> mHotInformations = new ArrayList<>();
    public InformationLoadTask(Callback callback){
        mCallback = callback;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        //请求HTTP数据
        try{
            URL url = new URL(ConstData.HEADER_INFO_URL);
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
            JSONArray headerArray =  headerObject.getJSONArray("dataList");
            for(int i = 0; i < headerArray.length(); ++i){
                HotInformation information = new HotInformation();
                information.setTitle(headerArray.getJSONObject(i).getString("title"));
                information.setUrl(headerArray.getJSONObject(i).getString("url"));
                mHotInformations.add(information);
            }

            url = new URL(ConstData.COLOR_INFO_URL);
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
            headerArray =  headerObject.getJSONArray("dataList");
            for(int i = 0; i < headerArray.length(); ++i){
                HotInformation information = new HotInformation();
                information.setTitle(headerArray.getJSONObject(i).getString("title"));
                information.setUrl(headerArray.getJSONObject(i).getString("url"));
                mHotInformations.add(information);
            }


            url = new URL(ConstData.WELFARE_INFO_URL);
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
            headerArray =  headerObject.getJSONArray("dataList");
            for(int i = 0; i < headerArray.length(); ++i){
                HotInformation information = new HotInformation();
                information.setTitle(headerArray.getJSONObject(i).getString("title"));
                information.setUrl(headerArray.getJSONObject(i).getString("url"));
                mHotInformations.add(information);
            }


            url = new URL(ConstData.POLICY_INFO_URL);
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
            headerArray =  headerObject.getJSONArray("dataList");
            for(int i = 0; i < headerArray.length(); ++i){
                HotInformation information = new HotInformation();
                information.setTitle(headerArray.getJSONObject(i).getString("title"));
                information.setUrl(headerArray.getJSONObject(i).getString("url"));
                mHotInformations.add(information);
            }


            url = new URL(ConstData.REWARD_INFO_URL);
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
            headerArray =  headerObject.getJSONArray("dataList");
            for(int i = 0; i < headerArray.length(); ++i){
                HotInformation information = new HotInformation();
                information.setTitle(headerArray.getJSONObject(i).getString("title"));
                information.setUrl(headerArray.getJSONObject(i).getString("url"));
                mHotInformations.add(information);
            }

        }catch (Exception e){

        }



        return  1;
    }

    @Override
    protected void onPostExecute(Integer integer) {
       mCallback.onGetResult(mHotInformations);
    }
}
