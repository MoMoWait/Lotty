package fjnu.edu.cn.xjsscttjh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.adapter.ColorTypeAdapter;
import fjnu.edu.cn.xjsscttjh.adapter.ExchangeAdapter;
import fjnu.edu.cn.xjsscttjh.base.AppBaseFragment;
import fjnu.edu.cn.xjsscttjh.bean.CurrencyInfo;
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
 * 汇率查询页面
 */
@ContentView(R.layout.fragment_exchange_search)
public class ExchangeSearchFragment extends AppBaseFragment {

    public interface LoadAllCurrencyService {
        @GET("currency")
        Call<ResponseBody> getResult(@Query("appkey") String appKey);
    }

    public interface CurrencyExchangeService {
        @GET("convert")
        Call<ResponseBody> getResult(@Query("appkey") String appKey, @Query("from") String from, @Query("to") String to, @Query("amount") double amount);
    }

    @ViewInject(R.id.spinner_before_change)
    private Spinner mSpinnerBeforeChange;
    @ViewInject(R.id.spinner_later_change)
    private Spinner mSpinnerLaterChange;
    @ViewInject(R.id.edt_amount)
    private EditText mEdtAmount;
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
        loadAllCurrency();
        initEvent();
    }

    /**
     * 加载所有币种
     */
    private void loadAllCurrency(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ConstData.BASE_EXCHANGE_URL).build();
        LoadAllCurrencyService allCurrencyService = retrofit.create(LoadAllCurrencyService.class);
        Call<ResponseBody> call = allCurrencyService.getResult(ConstData.JS_APP_KEY);
        mBtnSearch.setEnabled(false);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                DialogUtils.closeLoadingDialog();
                try {
                    ResponseBody body = response.body();
                    if(body != null){
                        String res = body.string();
                        JSONArray jsonDatas = new JSONObject(res).getJSONArray("result");
                        List<CurrencyInfo> currencyInfos = new ArrayList<CurrencyInfo>(jsonDatas.length());
                        for(int i = 0; i < jsonDatas.length(); ++i){
                            JSONObject itemJsonObject = jsonDatas.getJSONObject(i);
                            CurrencyInfo itemInfo = new CurrencyInfo();
                            itemInfo.setUnit(itemJsonObject.getString("currency"));
                            itemInfo.setName(itemJsonObject.getString("name"));
                            currencyInfos.add(itemInfo);
                        }
                        ExchangeAdapter beforeAdapter = new ExchangeAdapter(getContext(), android.R.layout.simple_spinner_item, currencyInfos);
                        beforeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        mSpinnerBeforeChange.setAdapter(beforeAdapter);

                        ExchangeAdapter laterAdapter = new ExchangeAdapter(getContext(), android.R.layout.simple_spinner_item, currencyInfos);
                        laterAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        mSpinnerLaterChange.setAdapter(laterAdapter);

                        mBtnSearch.setEnabled(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * 初始化监听事件
     */
    private void initEvent(){
        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strAmount = mEdtAmount.getText().toString();
                if(TextUtils.isEmpty(strAmount)){
                    ToastUtils.showToast(getString(R.string.enter_amount_tip));
                    return;
                }
                final double amount = Double.valueOf(strAmount);
                String beforeUnit = ((CurrencyInfo)mSpinnerBeforeChange.getSelectedItem()).getUnit();
                String laterUnit = ((CurrencyInfo)mSpinnerLaterChange.getSelectedItem()).getUnit();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(ConstData.BASE_EXCHANGE_URL).build();
                CurrencyExchangeService exchangeService = retrofit.create(CurrencyExchangeService.class);
                Call<ResponseBody> call = exchangeService.getResult(ConstData.JS_APP_KEY, beforeUnit, laterUnit, amount);
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
                                builder.append("转换:").append(resJsonObject.getString("fromname")).append("-->").append(resJsonObject.getString("toname")).append("\n")
                                        .append("数量:").append(amount).append("-->").append(resJsonObject.getString("camount")).append("\n")
                                        .append("汇率:").append(resJsonObject.getString("rate"));
                                mTextRes.setText(builder.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            mTextRes.setText(R.string.exchange_error);
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
