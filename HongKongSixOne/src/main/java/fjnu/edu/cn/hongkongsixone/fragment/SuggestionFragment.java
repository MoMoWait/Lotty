package fjnu.edu.cn.hongkongsixone.fragment;

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

import fjnu.edu.cn.hongkongsixone.R;
import fjnu.edu.cn.hongkongsixone.data.ConstData;
import fjnu.edu.cn.hongkongsixone.task.RegisterUserTask;
import fjnu.edu.cn.hongkongsixone.task.SuggestionUploadTask;
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

    private SuggestionUploadTask mLoadTask;

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
                mLoadTask = new SuggestionUploadTask(new RegisterUserTask.Callback() {
                    @Override
                    public void onResult(int status) {
                        DialogUtils.closeLoadingDialog();
                        if(status == ConstData.TaskResult.SUCC){
                            ToastUtils.showToast(getString(R.string.suggestion_succ));
                            getActivity().finish();
                        }else{
                            ToastUtils.showToast(getString(R.string.suggestion_failed));
                        }
                    }
                });
                mLoadTask.execute(suggestion, phone);

            }
        });
    }

}
