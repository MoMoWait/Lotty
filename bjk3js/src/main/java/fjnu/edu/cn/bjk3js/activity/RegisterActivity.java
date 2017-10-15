package fjnu.edu.cn.bjk3js.activity;


import android.support.v4.app.Fragment;

import fjnu.edu.cn.bjk3js.fragment.RegisterFragment;

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
