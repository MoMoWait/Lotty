package fjnu.edu.cn.xjsscttjh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.base.AppBaseFragment;
import fjnu.edu.cn.xjsscttjh.data.ConstData;
import momo.cn.edu.fjnu.androidutils.utils.DialogUtils;
import momo.cn.edu.fjnu.androidutils.utils.ToastUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/11/24.
 * 天气查询页面
 */
@ContentView(R.layout.fragment_weathcer_search)
public class WeatherSearchFragment extends AppBaseFragment {
    public interface WeatherSearchService {
        @GET("query")
        Call<ResponseBody> getResult(@Query("appkey") String appKey, @Query(value = "city", encoded = true) String city);
    }

    @ViewInject(R.id.edt_city)
    private EditText mEdtCity;
    @ViewInject(R.id.btn_search)
    private Button mBtnSearch;
    @ViewInject(R.id.text_res)
    private TextView mTextRes;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void init() {
        super.init();
        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = mEdtCity.getText().toString();
                if(TextUtils.isEmpty(cityName)){
                    ToastUtils.showToast(getString(R.string.enter_city_tip));
                    return;
                }
                Retrofit retrofit = new Retrofit.Builder().baseUrl(ConstData.BASE_WEATHER_URL).build();
                WeatherSearchService weatherService = retrofit.create(WeatherSearchService.class);
                Call<ResponseBody> call = weatherService.getResult(ConstData.JS_APP_KEY, cityName);
                DialogUtils.showLoadingDialog(getContext(), false);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        DialogUtils.closeLoadingDialog();
                        try {
                            ResponseBody body = response.body();
                            if(body != null){
                                String res = body.string();
                                JSONObject resJsonObject = new JSONObject(res).getJSONObject("result");
                                StringBuilder builder = new StringBuilder();
                                builder.append("日期:").append(resJsonObject.getString("date"))
                                        .append(" ").append(resJsonObject.getString("week")).append("\n")
                                        .append("当前温度:").append(resJsonObject.getString("temp")).append("\n")
                                        .append("最高温:").append(resJsonObject.getString("temphigh")).append("\n")
                                        .append("最低温:").append(resJsonObject.getString("templow")).append("\n")
                                        .append("风向:").append(resJsonObject.getString("winddirect")).append("\n")
                                        .append("风力:").append(resJsonObject.getString("windpower"));
                                mTextRes.setText(builder.toString());

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            mTextRes.setText(R.string.unknow_weather);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        DialogUtils.closeLoadingDialog();
                    }
                });
            }
        });
    }
}
