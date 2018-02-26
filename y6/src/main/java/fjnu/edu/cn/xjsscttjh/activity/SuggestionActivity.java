package fjnu.edu.cn.xjsscttjh.activity;

import android.support.v4.app.Fragment;

import fjnu.edu.cn.xjsscttjh.fragment.SuggestionFragment;


/**
 * Created by Administrator on 2017\9\4 0004.
 * 意见反馈
 */

public class SuggestionActivity extends SingleFragmentActivity{
    @Override
    public Fragment createFragment() {
        return new SuggestionFragment();
    }
}
