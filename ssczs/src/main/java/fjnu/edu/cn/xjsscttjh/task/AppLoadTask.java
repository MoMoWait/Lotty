package fjnu.edu.cn.xjsscttjh.task;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import fjnu.edu.cn.xjsscttjh.data.ConstData;


/**
 * Created by Administrator on 2017\9\2 0002.
 * 异步块，验证是否加载APP
 */

public class AppLoadTask extends AsyncTask<String, Integer, Integer>{

    public interface Callback{
        void onResult(int status);
        void showNetWorkError();
    }

    private Callback mCallback;
    private Exception mException;

    public AppLoadTask(Callback callback){
        mCallback = callback;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        //请求HTTP数据
        try{
            URL url = new URL(ConstData.APP_LOAD_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            byte data[] = new byte[2048];
            InputStream stream = connection.getInputStream();
            int readLength = stream.read(data);
            stream.close();
            String content = new String(data, 0, readLength, Charset.forName("UTF-8"));
            JSONObject contentObject = new JSONObject(content);
            return contentObject.getInt("status");
        }catch (Exception e){
            mException = e;
        }
        return  1;

    }

    @Override
    protected void onPostExecute(Integer result) {
        if(mException != null)
            mCallback.showNetWorkError();
        mCallback.onResult(result);

    }
}
