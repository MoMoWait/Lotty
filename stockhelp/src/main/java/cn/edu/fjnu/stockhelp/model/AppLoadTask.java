package cn.edu.fjnu.stockhelp.model;
import android.os.AsyncTask;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import cn.edu.fjnu.stockhelp.data.ConstData;
import momo.cn.edu.fjnu.androidutils.utils.ToastUtils;


/**
 * Created by Administrator on 2017\9\2 0002.
 * 异步块，验证是否加载APP
 */

public class AppLoadTask extends AsyncTask<String, Integer, Integer>{

    public interface Callback{
        void onResult(int status);
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
          /*  StringReader reader = new StringReader(content);
            BufferedReader bufferedReader  = new BufferedReader(reader);
            bufferedReader.readLine();
            String statusLine = bufferedReader.readLine();
            return Integer.parseInt(statusLine.substring(statusLine.indexOf(":") + 1, statusLine.indexOf(",")).trim());*/
        }catch (Exception e){
            mException = e;
        }
        return  1;

    }

    @Override
    protected void onPostExecute(Integer result) {
        if(mException != null)
            ToastUtils.showToast("发生异常:" + mException);
        mCallback.onResult(result);

    }
}
