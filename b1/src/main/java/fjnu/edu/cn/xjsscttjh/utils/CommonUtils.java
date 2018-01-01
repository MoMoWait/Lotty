package fjnu.edu.cn.xjsscttjh.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import fjnu.edu.cn.xjsscttjh.bean.Message;
import fjnu.edu.cn.xjsscttjh.data.ConstData;

/**
 * Created by GaoFei on 2017/12/31.
 * 通用工具类
 */

public class CommonUtils {
    private CommonUtils(){

    }

    public static List<Message> getMessages(){
        List<Message> infos = new ArrayList<>();
        //请求HTTP数据
        try{
            URL oneURL = new URL(ConstData.HEADER_INFO_URL);
            HttpURLConnection connection = (HttpURLConnection) oneURL.openConnection();
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
            JSONObject oneObject = new JSONObject(headContent);
            JSONArray oneArray =  oneObject.getJSONArray("dataList");
            for(int i = 0; i < oneArray.length(); ++i){
                Message message = new Message();
                JSONObject itemObject = oneArray.getJSONObject(i);
                message.setTitle(itemObject.getString("title"));
                message.setPicUrl(itemObject.getString("logoFile"));
                message.setContentUrl(itemObject.getString("url"));
                infos.add(message);
            }


            URL twoURL = new URL(ConstData.COLOR_INFO_URL);
            connection = (HttpURLConnection) twoURL.openConnection();
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
            JSONObject twoObject = new JSONObject(headContent);
            JSONArray twoArray =  twoObject.getJSONArray("dataList");
            for(int i = 0; i < twoArray.length(); ++i){
                Message message = new Message();
                JSONObject itemObject = twoArray.getJSONObject(i);
                message.setTitle(itemObject.getString("title"));
                message.setPicUrl(itemObject.getString("logoFile"));
                message.setContentUrl(itemObject.getString("url"));
                infos.add(message);
            }


            URL threeURL = new URL(ConstData.WELFARE_INFO_URL);
            connection = (HttpURLConnection) threeURL.openConnection();
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
            JSONObject threeObject = new JSONObject(headContent);
            JSONArray threeArray =  threeObject.getJSONArray("dataList");
            for(int i = 0; i < twoArray.length(); ++i){
                Message message = new Message();
                JSONObject itemObject = threeArray.getJSONObject(i);
                message.setTitle(itemObject.getString("title"));
                message.setPicUrl(itemObject.getString("logoFile"));
                message.setContentUrl(itemObject.getString("url"));
                infos.add(message);
            }



        }catch (Exception e){

        }
        return  infos;
    }
}
