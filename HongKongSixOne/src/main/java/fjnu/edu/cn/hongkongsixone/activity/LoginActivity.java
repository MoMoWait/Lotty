package fjnu.edu.cn.hongkongsixone.activity;


import android.support.v4.app.Fragment;

import fjnu.edu.cn.hongkongsixone.fragment.LoginFragment;


/**
 * 登录页面
 * Created by GaoFei on 2016/3/7.
 */
public class LoginActivity extends SingleFragmentActivity{
    @Override
    public Fragment createFragment() {
        return new LoginFragment();
    }
}
