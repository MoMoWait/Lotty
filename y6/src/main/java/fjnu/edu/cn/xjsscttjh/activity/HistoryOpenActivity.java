package fjnu.edu.cn.xjsscttjh.activity;

import android.support.v4.app.Fragment;

import fjnu.edu.cn.xjsscttjh.base.AppBaseActivity;
import fjnu.edu.cn.xjsscttjh.fragment.HistoryOpenFragment;

/**
 * Created by gaofei on 2018/2/3.
 * 历史开奖页面
 */

public class HistoryOpenActivity extends SingleFragmentActivity{
    @Override
    public Fragment createFragment() {
        return new HistoryOpenFragment();
    }
}
