package cn.edu.fjnu.realtimecolor.activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import cn.edu.fjnu.realtimecolor.fragment.InitFragment;

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
