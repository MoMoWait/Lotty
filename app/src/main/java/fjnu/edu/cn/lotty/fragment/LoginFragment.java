package fjnu.edu.cn.lotty.fragment;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import fjnu.edu.cn.lotty.R;
import momo.cn.edu.fjnu.androidutils.base.BaseFragment;
import momo.cn.edu.fjnu.androidutils.utils.DialogUtils;
import momo.cn.edu.fjnu.androidutils.utils.ToastUtils;


/**
 * 登录页面及相关处理
 * Created by GaoFei on 2016/3/7.
 */
@ContentView(R.layout.fragment_login)
public class LoginFragment extends BaseFragment{

    public final String TAG = "LoginFragment";
    /**登陆按钮*/
    @ViewInject(R.id.btn_login)
    private TextView mBtnLogin;
    @ViewInject(R.id.edt_user_name)
    private EditText mEdtUserName;
    @ViewInject(R.id.edt_password)
    private EditText mEdtPassword;
    @ViewInject(R.id.img_user_head)
    private ImageView mImgUserHead;

    private String mUserName;
    private String mPasswd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void init() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = mEdtUserName.getText().toString();
                String passwd = mEdtPassword.getText().toString();
                mUserName = userName;
                mPasswd = passwd;
                if(android.text.TextUtils.isEmpty(userName)){
                    ToastUtils.showToast("请输入用户名");
                    return;
                }
                if(android.text.TextUtils.isEmpty(passwd)){
                    ToastUtils.showToast("请输入密码");
                    return;
                }
                login();
            }
        });
    }


    public void login(){
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
                if(mUserName.equals("GaoFei") && mPasswd.equals("123456")){
                    getActivity().setResult(Activity.RESULT_OK);
                    getActivity().finish();
                }else{
                    ToastUtils.showToast("用户名或密码错误");
                }

            }
        }.execute();
    }
}
