package fjnu.edu.cn.hongkongsixtwo.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import fjnu.edu.cn.hongkongsixtwo.R;
import fjnu.edu.cn.hongkongsixtwo.base.AppBaseFragment;
import fjnu.edu.cn.hongkongsixtwo.bean.UserInfo;
import fjnu.edu.cn.hongkongsixtwo.data.ConstData;
import fjnu.edu.cn.hongkongsixtwo.task.LoginTask;
import fjnu.edu.cn.hongkongsixtwo.view.LoginActionBarView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import momo.cn.edu.fjnu.androidutils.utils.DialogUtils;
import momo.cn.edu.fjnu.androidutils.utils.ToastUtils;


/**
 * 登录页面及相关处理
 * Created by GaoFei on 2016/3/7.
 */
@ContentView(R.layout.fragment_login)
public class LoginFragment extends AppBaseFragment{

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
    private LoginTask mLoginTask;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void init() {
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(new LoginActionBarView(getContext()));
        }
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
        mLoginTask = new LoginTask();
        UserInfo info = new UserInfo();
        info.setUserName(mUserName);
        info.setPasswd(mPasswd);
        Observable.just(info).map(new Function<UserInfo, Integer>() {
            @Override
            public Integer apply(@NonNull UserInfo userInfo) throws Exception {
                mLoginTask = new LoginTask();
                return mLoginTask.login(userInfo.getUserName(), userInfo.getPasswd());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer status) throws Exception {
                DialogUtils.closeLoadingDialog();
                if(status == ConstData.TaskResult.SUCC){
                    Intent resultData = new Intent();
                    resultData.putExtra(ConstData.IntentKey.USER_NAME, mUserName);
                    getActivity().setResult(Activity.RESULT_OK, resultData);
                    getActivity().finish();
                }else{
                    ToastUtils.showToast(getString(R.string.login_failed));
                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        //makeActionBarCenterText();
        //mTextRegister = addActionBarRightText(getString(R.string.register));
    }

    @Override
    public void onPause() {
        super.onPause();
        //removeActionBarText(mTextRegister);
    }
}
