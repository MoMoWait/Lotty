package fjnu.edu.cn.lotty.fragment;

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
import fjnu.edu.cn.lotty.base.AppBaseFragment;
import fjnu.edu.cn.lotty.data.ConstData;
import fjnu.edu.cn.lotty.task.RegisterUserTask;
import momo.cn.edu.fjnu.androidutils.utils.DialogUtils;
import momo.cn.edu.fjnu.androidutils.utils.ToastUtils;

/**
 * Created by Administrator on 2017\9\14 0014.
 * 注册页面
 */

@ContentView(R.layout.fragment_register)
public class RegisterFragment extends AppBaseFragment {

    @ViewInject(R.id.edit_user_name)
    private EditText mEditUserName;
    @ViewInject(R.id.edit_password)
    private EditText mEditPassword;
    @ViewInject(R.id.edit_confirm_password)
    private EditText mEditConfirmPassword;
    @ViewInject(R.id.btn_register)
    private Button mBtnRegister;

    private RegisterUserTask mRegisterTask;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void init() {
        mRegisterTask = new RegisterUserTask(new RegisterUserTask.Callback() {
            @Override
            public void onResult(int status) {
                DialogUtils.closeLoadingDialog();
                if(status == ConstData.TaskResult.SUCC){
                    ToastUtils.showToast(getString(R.string.register_succ));
                    getActivity().finish();
                }else{
                    ToastUtils.showToast(getString(R.string.register_failed));
                }
            }
        });
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = mEditUserName.getText().toString().trim();
                String password = mEditPassword.getText().toString();
                String confirmPassword = mEditConfirmPassword.getText().toString();
                if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)){
                    ToastUtils.showToast(getString(R.string.input_all));
                    return;
                }
                if(!password.equals(confirmPassword)){
                    ToastUtils.showToast(getString(R.string.password_not_same));
                    return;
                }
                DialogUtils.showLoadingDialog(getContext(), false);
                mRegisterTask.execute(userName, password);
            }
        });
    }
}
