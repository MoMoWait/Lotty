package fjnu.edu.cn.xjsscttjh.activity;

import android.support.v4.app.Fragment;

import fjnu.edu.cn.xjsscttjh.fragment.WyJoinMethodDisplayFragment;
import fjnu.edu.cn.xjsscttjh.fragment.WyJoinMethodFragment;

/**
 * Created by gaofei on 2018/2/9.
 * 网易玩法介绍显示页面
 */

public class WyJoinMethodDisplayActivity extends SingleFragmentActivity{
    @Override
    public Fragment createFragment() {
        return new WyJoinMethodDisplayFragment();
    }
}
