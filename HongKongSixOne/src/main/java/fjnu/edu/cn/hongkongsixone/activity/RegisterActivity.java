package fjnu.edu.cn.hongkongsixone.activity;


import android.support.v4.app.Fragment;

import fjnu.edu.cn.hongkongsixone.fragment.RegisterFragment;

/**
 * Created by Administrator on 2017\9\14 0014.
 * 注册页
 */

public class RegisterActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return new RegisterFragment();
    }
}
