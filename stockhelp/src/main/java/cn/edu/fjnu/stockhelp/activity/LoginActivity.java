package cn.edu.fjnu.stockhelp.activity;


import android.support.v4.app.Fragment;

import cn.edu.fjnu.stockhelp.fragment.LoginFragment;


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
