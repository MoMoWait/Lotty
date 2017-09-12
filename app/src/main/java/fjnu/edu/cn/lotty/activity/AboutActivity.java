package fjnu.edu.cn.lotty.activity;

import android.support.v4.app.Fragment;

import fjnu.edu.cn.lotty.fragment.AboutFragment;


/**
 * Created by Administrator on 2017\9\4 0004.
 * 关于我们
 */

public class AboutActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return new AboutFragment();
    }
}
