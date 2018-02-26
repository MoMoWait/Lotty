package fjnu.edu.cn.xjsscttjh.activity;

import android.support.v4.app.Fragment;

import fjnu.edu.cn.xjsscttjh.fragment.ForcaestInfoDisplayFragment;

/**
 * Created by gaofei on 2018/2/4.
 * 预测消息显示
 */

public class ForcaestInfoDisplayActivity extends SingleFragmentActivity{
    @Override
    public Fragment createFragment() {
        return new ForcaestInfoDisplayFragment();
    }
}
