package fjnu.edu.cn.xjsscttjh.activity;

import android.support.v4.app.Fragment;

import fjnu.edu.cn.xjsscttjh.fragment.WyJoinMethodFragment;

/**
 * Created by gaofei on 2018/2/8.
 * 网易玩法页面介绍
 */

public class WyJoinMethodActivity extends SingleFragmentActivity{
    @Override
    public Fragment createFragment() {
        return new WyJoinMethodFragment();
    }
}
