package fjnu.edu.cn.xjsscttjh.activity;

import android.support.v4.app.Fragment;

import fjnu.edu.cn.xjsscttjh.fragment.FcTrendChartBrowserFragment;

/**
 * Created by gaofei on 2018/1/31.
 * 图表走势页面
 */

public class FcTrendChartActivity extends SingleFragmentActivity{
    @Override
    public Fragment createFragment() {
        return new FcTrendChartBrowserFragment();
    }
}
