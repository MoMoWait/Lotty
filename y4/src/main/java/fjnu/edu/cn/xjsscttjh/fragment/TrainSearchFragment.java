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

import org.json.JSONArray;
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
 * 火车查询
 */
@ContentView(R.layout.fragment_train_search)
public class TrainSearchFragment extends AppBaseFragment {

    public interface TrainSearchService {
        @GET("line")
        Call<ResponseBody> getResult(@Query("appkey") String appKey, @Query("trainno") String trainNo);
    }

    @ViewInject(R.id.edt_train)
    private EditText mEdtTrain;
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
                String trainNo = mEdtTrain.getText().toString();
                if(TextUtils.isEmpty(trainNo)){
                    ToastUtils.showToast(getString(R.string.enter_train));
                    return;
                }
                Retrofit retrofit = new Retrofit.Builder().baseUrl(ConstData.BASE_TRAIN_URL).build();
                TrainSearchService trainService = retrofit.create(TrainSearchService.class);
                Call<ResponseBody> call = trainService.getResult(ConstData.JS_APP_KEY, trainNo);
                DialogUtils.showLoadingDialog(getContext(), false);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        DialogUtils.closeLoadingDialog();
                        try {
                            ResponseBody body = response.body();
                            if(body != null){
                                String res = body.string();
                                JSONArray resArray = new JSONObject(res).getJSONObject("result").getJSONArray("list");
                                StringBuilder builder = new StringBuilder();
                                builder.append("出发时间:").append(resArray.getJSONObject(0).getString("departuretime")).append("\n")
                                        .append("到达时间:").append(resArray.getJSONObject(resArray.length() - 1).getString("arrivaltime")).append("\n")
                                        .append("经过站次:");
                                for(int i = 0; i < resArray.length(); ++i){
                                    JSONObject itemJsonObject = resArray.getJSONObject(i);
                                    builder.append(itemJsonObject.getString("station"));
                                    if(i == 0)
                                        builder.append("(").append(itemJsonObject.getString("departuretime")).append(")");
                                    else
                                        builder.append("(").append(itemJsonObject.getString("arrivaltime")).append(")");
                                    if(i + 1 != resArray.length()){
                                        builder.append("-->");
                                    }
                                }
                                mTextRes.setText(builder.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            mTextRes.setText(R.string.trainno_error);
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
