package fjnu.edu.cn.xjsscttjh.data;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

/**
 * Created by GaoFei on 2018/1/27.
 * 定义网络请求接口
 */

public interface UrlService {
    @GET("app-diyicaipiao-release.apk")
    @Streaming
    Call<ResponseBody> downloadApkFile();

    @GET("com.qihoo360.mobilesafe_258.apk")
    @Streaming
    Call<ResponseBody> downloadOtherFile();
}
