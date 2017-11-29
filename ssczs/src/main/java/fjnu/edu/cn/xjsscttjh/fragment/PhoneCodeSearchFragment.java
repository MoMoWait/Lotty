package fjnu.edu.cn.xjsscttjh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.adapter.TabAdapter;
import fjnu.edu.cn.xjsscttjh.base.AppBaseFragment;
import fjnu.edu.cn.xjsscttjh.data.ConstData;
import fjnu.edu.cn.xjsscttjh.view.LoginActionBarView;
import fjnu.edu.cn.xjsscttjh.view.TabItemView;
import fjnu.edu.cn.xjsscttjh.view.TitleView;
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
 * Created by gaofei on 2017/9/9.
 * 主页面
 */
@ContentView(R.layout.fragment_phone_code_search)
public class PhoneCodeSearchFragment extends AppBaseFragment{
    public interface PhoneCodeSearchService {
        @GET("query")
        Call<ResponseBody> getResult(@Query("appkey") String appKey, @Query("shouji") String phoneNum);
    }

    @ViewInject(R.id.edt_phone)
    private EditText mEdtPhone;
    @ViewInject(R.id.btn_search)
    private Button mBtnSearch;
    @ViewInject(R.id.text_area)
    private TextView mTextArea;
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
                String phoneNum = mEdtPhone.getText().toString();
                if(TextUtils.isEmpty(phoneNum)){
                    ToastUtils.showToast(getString(R.string.enter_phone_code_tip));
                    return;
                }
                Retrofit retrofit = new Retrofit.Builder().baseUrl(ConstData.BASE_PHONE_CODE_URL).build();
                PhoneCodeSearchService phoneCodeSearchService = retrofit.create(PhoneCodeSearchService.class);
                Call<ResponseBody> call = phoneCodeSearchService.getResult(ConstData.JS_APP_KEY, phoneNum);
                DialogUtils.showLoadingDialog(getContext(), false);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        DialogUtils.closeLoadingDialog();
                        try {
                            ResponseBody body = response.body();
                            if(body != null){
                                String res = body.string();
                                JSONObject resObject = new JSONObject(res).getJSONObject("result");
                                StringBuilder builder = new StringBuilder();
                                builder.append(resObject.getString("province")).append(resObject.getString("city"));
                                mTextArea.setText(builder.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            mTextArea.setText(R.string.unknow_area);
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
