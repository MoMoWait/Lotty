package fjnu.edu.cn.lotty.activity;


import android.support.v4.app.Fragment;

import fjnu.edu.cn.lotty.fragment.RegisterFragment;

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
