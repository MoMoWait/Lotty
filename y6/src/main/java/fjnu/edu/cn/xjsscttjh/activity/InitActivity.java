package fjnu.edu.cn.xjsscttjh.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.WindowManager;

import fjnu.edu.cn.xjsscttjh.fragment.InitFragment;

/**
 * Created by Administrator on 2017\9\2 0002.
 * 启动页
 */

public class InitActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    public Fragment createFragment() {
        return new InitFragment();
    }
}
