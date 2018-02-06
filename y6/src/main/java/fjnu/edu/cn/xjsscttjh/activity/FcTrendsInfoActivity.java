package fjnu.edu.cn.xjsscttjh.activity;

import android.support.v4.app.Fragment;

import fjnu.edu.cn.xjsscttjh.fragment.FcTrendsInfoFragment;

/**
 * Created by gaofei on 2018/1/31.
 * 发彩网走势页面
 */

public class FcTrendsInfoActivity extends SingleFragmentActivity{
    @Override
    public Fragment createFragment() {
        return new FcTrendsInfoFragment();
    }
}
