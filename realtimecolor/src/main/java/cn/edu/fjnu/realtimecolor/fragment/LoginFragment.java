package cn.edu.fjnu.realtimecolor.fragment;
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
import cn.edu.fjnu.realtimecolor.R;
import momo.cn.edu.fjnu.androidutils.base.BaseFragment;
import momo.cn.edu.fjnu.androidutils.utils.DialogUtils;
import momo.cn.edu.fjnu.androidutils.utils.ResourceUtils;
import momo.cn.edu.fjnu.androidutils.utils.ToastUtils;


/**
 * 登录页面及相关处理
 * Created by GaoFei on 2016/3/7.
 */
@ContentView(R.layout.fragment_login)
public class LoginFragment extends BaseFragment{

    public final String TAG = LoginFragment.class.getSimpleName();

    @ViewInject(R.id.img_back)
    private ImageView mImgBack;
    @ViewInject(R.id.text_title)
    private TextView mTextTitle;
    @ViewInject(R.id.text_option)
    private TextView mTextOption;
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
        initView();
        initEvent();
    }

    public void initView() {
        //隐藏按钮
        mImgBack.setVisibility(View.GONE);
        mTextOption.setVisibility(View.GONE);
        //mTextOption.setText(ResourceUtils.getString(R.string.register));
        //设置标题
        mTextTitle.setText(ResourceUtils.getString(R.string.user_login));
        //处理用户头像为圆角
        //mImgUserHead.setImageBitmap(BitmapUtils.getCroppedBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.img_default_head)));
    }

    public void initEvent() {
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
