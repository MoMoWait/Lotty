package fjnu.edu.cn.xjsscttjh.activity;

import android.support.v4.app.Fragment;

import fjnu.edu.cn.xjsscttjh.fragment.FCTrendsTypeFramgnet;

/**
 * Created by gaofei on 2018/2/7.
 * 发彩网走势页面
 */

public class FCTrendsTypeActivity extends SingleFragmentActivity{
    @Override
    public Fragment createFragment() {
        return new FCTrendsTypeFramgnet();
    }
}
