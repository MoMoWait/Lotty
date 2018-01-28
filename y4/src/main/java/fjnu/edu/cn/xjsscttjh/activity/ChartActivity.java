package fjnu.edu.cn.xjsscttjh.activity;

import android.support.v4.app.Fragment;

import fjnu.edu.cn.xjsscttjh.fragment.ChartFragment;


/**
 * Created by gaofei on 2017/10/10.
 * 图表浏览页面
 */

public class ChartActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return new ChartFragment();
    }
}
