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
 * 区号查询
 */
@ContentView(R.layout.fragment_area_code_search)
public class AreaCodeSearchFragment extends AppBaseFragment implements View.OnClickListener{

    public interface CodeToAreaService{
        @GET("query")
        Call<ResponseBody> getResult(@Query("appkey") String appKey, @Query("areacode") String code);
    }

    public interface AreaToCodeService{
        @GET("city2code")
        Call<ResponseBody> getResult(@Query(value = "appkey") String appKey, @Query(value = "city", encoded = true) String city);
    }


    @ViewInject(R.id.edt_code)
    private EditText mEdtCode;
    @ViewInject(R.id.btn_area_search)
    private Button mBtnAreaSearch;
    @ViewInject(R.id.text_area)
    private TextView mTextArea;

    @ViewInject(R.id.edt_area)
    private EditText mEdtArea;
    @ViewInject(R.id.btn_code_search)
    private Button mBtnCodeSearch;
    @ViewInject(R.id.text_code)
    private TextView mTextCode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void init() {
        super.init();
        mBtnAreaSearch.setOnClickListener(this);
        mBtnCodeSearch.setOnClickListener(this);
    }

    @Override
    public CharSequence getActivityLable() {
        return super.getActivityLable();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_area_search:
                String code = mEdtCode.getText().toString();
                if(TextUtils.isEmpty(code)){
                    ToastUtils.showToast(getString(R.string.enter_code_tip));
                    return;
                }
                DialogUtils.showLoadingDialog(getContext(), false);
                Retrofit retrofit = new Retrofit.Builder().baseUrl(ConstData.BASE_AREA_CODE_URL).build();
                CodeToAreaService codeToAreaService = retrofit.create(CodeToAreaService.class);
                Call<ResponseBody> call = codeToAreaService.getResult(ConstData.JS_APP_KEY, code);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        DialogUtils.closeLoadingDialog();
                        ResponseBody body = response.body();
                        try{
                            if(body != null){
                                JSONObject resObject = new JSONObject(body.string()).getJSONArray("result").getJSONObject(0);
                                String provice = resObject.getString("province");
                                String city = resObject.getString("city");
                                mTextArea.setText("" + provice + city);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            mTextArea.setText(R.string.unknow_area);
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        DialogUtils.closeLoadingDialog();
                    }
                });
                break;
            case R.id.btn_code_search:
                String area = mEdtArea.getText().toString();
                if(TextUtils.isEmpty(area)){
                    ToastUtils.showToast(getString(R.string.enter_city_tip));
                    return;
                }
                DialogUtils.showLoadingDialog(getContext(), false);
                Retrofit retrofit2 = new Retrofit.Builder().baseUrl(ConstData.BASE_AREA_CODE_URL).build();
                AreaToCodeService areaToCodeService = retrofit2.create(AreaToCodeService.class);
                Call<ResponseBody> call2 = areaToCodeService.getResult(ConstData.JS_APP_KEY, area);
                call2.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        DialogUtils.closeLoadingDialog();
                        ResponseBody body = response.body();
                        try{
                            if(body != null){
                                JSONObject resObject = new JSONObject(body.string()).getJSONArray("result").getJSONObject(0);
                                String areaCode = resObject.getString("areacode");
                                mTextCode.setText(areaCode);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            mTextCode.setText(R.string.unknow_code);
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        DialogUtils.closeLoadingDialog();
                    }
                });
                break;
            default:
                break;
        }
    }
}
