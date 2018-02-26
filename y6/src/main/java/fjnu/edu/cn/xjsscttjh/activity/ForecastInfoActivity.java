package fjnu.edu.cn.xjsscttjh.activity;

import android.support.v4.app.Fragment;

import fjnu.edu.cn.xjsscttjh.fragment.ForcaestInfoFragment;

/**
 * Created by gaofei on 2018/2/7.
 * 预测消息
 */

public class ForecastInfoActivity extends SingleFragmentActivity{
    @Override
    public Fragment createFragment() {
        return new ForcaestInfoFragment();
    }
}
