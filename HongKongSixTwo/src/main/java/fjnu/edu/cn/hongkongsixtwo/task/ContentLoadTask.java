package fjnu.edu.cn.hongkongsixtwo.task;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import fjnu.edu.cn.hongkongsixtwo.data.ConstData;

/**
 * Created by Administrator on 2017\9\2 0002.
 * 异步块，验证是否加载APP内容
 */

public class ContentLoadTask extends AsyncTask<String, Integer, Integer> {
    public interface Callback{
        void onResult(int status, String url);
    }

    private String mLoadUrl;
    private Callback mCallback;

    public ContentLoadTask(Callback callback){
        mCallback = callback;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        //请求HTTP数据
        try{
            URL url = new URL(ConstData.APP_CONTEN_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            byte data[] = new byte[2048];
            InputStream stream = connection.getInputStream();
            int readLength = stream.read(data);
            stream.close();
            String content = new String(data, 0 , readLength, Charset.forName("UTF-8"));
            JSONObject object = new JSONObject(content);
            int status = object.getInt("status");
            if(status == 1){
                mLoadUrl = object.getString("url");
            }
            return status;
        }catch (Exception e){

        }
        return  1;

    }

    @Override
    protected void onPostExecute(Integer result) {
        mCallback.onResult(result, mLoadUrl);
    }
}
