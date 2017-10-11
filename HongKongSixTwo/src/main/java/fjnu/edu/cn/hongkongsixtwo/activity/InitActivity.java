package fjnu.edu.cn.hongkongsixtwo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import fjnu.edu.cn.hongkongsixtwo.fragment.InitFragment;

/**
 * Created by Administrator on 2017\9\2 0002.
 * 启动页
 */

public class InitActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Fragment createFragment() {
        return new InitFragment();
    }
}
