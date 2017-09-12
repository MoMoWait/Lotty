package cn.edu.fjnu.racingplan.model;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import cn.edu.fjnu.racingplan.data.ConstData;
import cn.edu.fjnu.racingplan.pojo.ColorInfo;

/**
 * Created by Administrator on 2017\9\5 0005.
 * 开奖信息获取,加载
 */

public class ColorInfoLoadTask extends AsyncTask<String, Integer, Integer> {

    public interface Callback{
        void onGetResult(List<ColorInfo> colorInfos);
    }

    private List<ColorInfo> mColorInfos = new ArrayList<>();
    private Callback mCallback;

    public ColorInfoLoadTask(Callback callback){
        mCallback = callback;
    }

    @Override
    protected Integer doInBackground(String... strings) {
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
            JSONArray headerArray =  headerObject.getJSONArray("historyLotteryResults");
            for(int i = 0; i < headerArray.length(); ++i){
                ColorInfo colorInfo = new ColorInfo();
                JSONObject itemObject = headerArray.getJSONObject(i);
                colorInfo.setOpenDate(itemObject.getString("time"));
                colorInfo.setIssueNo(itemObject.getString("issue"));
                colorInfo.setNumber(itemObject.getString("numbers"));
                mColorInfos.add(colorInfo);
            }

        }catch (Exception e){

        }
        return null;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        mCallback.onGetResult(mColorInfos);
    }
}
