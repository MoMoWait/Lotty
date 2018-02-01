package fjnu.edu.cn.xjsscttjh.task;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import fjnu.edu.cn.xjsscttjh.data.ConstData;
import fjnu.edu.cn.xjsscttjh.data.UrlService;
import momo.cn.edu.fjnu.androidutils.utils.FileUtils;
import momo.cn.edu.fjnu.androidutils.utils.StorageUtils;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by GaoFei on 2018/1/27.
 * APK下载器
 */

public class ApkDownloadProgressTask extends AsyncTask<String, Integer, Integer> {
    private  final String TAG = "ApkDownloadProgressTask";
    public interface Callback{
        void onProgressUpdate(int progress);
        void onResult(int res);
    }

    private Callback mCallBack;

    public ApkDownloadProgressTask(Callback callback){
        mCallBack = callback;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        //下载地址
        String downloadUrl = strings[0];
        try{
            //获取彩票包的md5值
            String lottyMd5 = StorageUtils.getDataFromSharedPreference(ConstData.SharedKey.LOTTY_APK_MD5);
            File lottyFile = new File(ConstData.LOCAL_LOTTY_APK_PATH);
            if(lottyFile.exists() &&  !TextUtils.isEmpty(lottyMd5) && lottyMd5.equals(FileUtils.getFileMD5(lottyFile))){
                return ConstData.TaskResult.SUCC;
            }
            OkHttpClient client = new OkHttpClient.Builder().
                    connectTimeout(30, TimeUnit.SECONDS).
                    readTimeout(6000, TimeUnit.SECONDS).
                    writeTimeout(30, TimeUnit.SECONDS).build();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(ConstData.BASE_LOTTY_APK_URL).client(client).build();
            UrlService urlService = retrofit.create(UrlService.class);
            Call<ResponseBody> call = urlService.downloadApkFile();
            retrofit2.Response<ResponseBody> response = call.execute();
            ResponseBody body = response.body();
            //URL url = new URL(downloadUrl);
            //HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //connection.setRequestMethod("GET");
            //connection.setConnectTimeout(30 * 1000);
            if(body == null)
                return ConstData.TaskResult.FAILED;
            InputStream inputStream = body.byteStream();
            FileOutputStream outputStream = new FileOutputStream(new File(ConstData.LOCAL_LOTTY_APK_PATH));
            long fileLength = body.contentLength();
            Log.i(TAG, "contentLength:" + fileLength);
            //ToastUtils.showToast("contentLength:" + fileLength);
            byte[] readBuf = new byte[4096];
            int readLength;
            int downloadLength = 0;
            while((readLength = inputStream.read(readBuf)) > 0){
                //更新进度
                downloadLength += readLength;
                publishProgress((int)(downloadLength * 1.0f / fileLength * 100));
                outputStream.write(readBuf, 0, readLength);
            }
            inputStream.close();
            outputStream.flush();
            outputStream.close();
            File downloadFile = new File(ConstData.LOCAL_LOTTY_APK_PATH);
            String md5 =  FileUtils.getFileMD5(downloadFile);
            StorageUtils.saveDataToSharedPreference(ConstData.SharedKey.LOTTY_APK_MD5, md5);
            return ConstData.TaskResult.SUCC;
        }catch (Exception e){
            //直接返回
            return ConstData.TaskResult.FAILED;
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mCallBack.onProgressUpdate(values[0]);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        mCallBack.onResult(integer);
    }
}
