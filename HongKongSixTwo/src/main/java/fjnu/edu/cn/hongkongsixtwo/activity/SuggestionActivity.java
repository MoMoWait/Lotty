package fjnu.edu.cn.hongkongsixtwo.activity;

import android.support.v4.app.Fragment;

import fjnu.edu.cn.hongkongsixtwo.fragment.SuggestionFragment;


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
