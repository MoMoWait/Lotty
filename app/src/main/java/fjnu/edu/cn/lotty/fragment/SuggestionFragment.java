package fjnu.edu.cn.lotty.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import fjnu.edu.cn.lotty.R;
import momo.cn.edu.fjnu.androidutils.base.BaseFragment;
import momo.cn.edu.fjnu.androidutils.utils.DialogUtils;
import momo.cn.edu.fjnu.androidutils.utils.ToastUtils;

/**
 * Created by Administrator on 2017\9\4 0004.
 * 意见反馈
 */

@ContentView(R.layout.fragment_suggestion)
public class SuggestionFragment extends BaseFragment {


    @ViewInject(R.id.edit_suggestion)
    private EditText mEditSuggestion;
    @ViewInject(R.id.edit_phone)
    private EditText mEditPhone;
    @ViewInject(R.id.btn_suggestion)
    private Button mBtnSuggestion;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void init() {
        mBtnSuggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String suggestion = mEditSuggestion.getText().toString();
                String phone = mEditPhone.getText().toString();
                if(TextUtils.isEmpty(suggestion)){
                    ToastUtils.showToast("请输入建议，反馈");
                    return;
                }
                DialogUtils.showLoadingDialog(getActivity(), false);
                new AsyncTask<String, Integer, Integer>(){
                    @Override
                    protected Integer doInBackground(String... strings) {
                        try {
                            java.util.concurrent.TimeUnit.MILLISECONDS.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Integer integer) {
                        DialogUtils.closeLoadingDialog();
                        ToastUtils.showToast("反馈成功");
                        getActivity().finish();
                    }
                }.execute();
            }
        });
    }

}
