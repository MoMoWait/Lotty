package fjnu.edu.cn.xjsscttjh.activity;

import android.support.v4.app.Fragment;
import fjnu.edu.cn.xjsscttjh.fragment.LotteryDisplayFragment;

/**
 * Created by gaofei on 2017/11/30.
 * 开奖显示页面
 */

public class LotteryDisplayActivity extends SingleFragmentActivity{
    @Override
    public Fragment createFragment() {
        return new LotteryDisplayFragment();
    }
}
