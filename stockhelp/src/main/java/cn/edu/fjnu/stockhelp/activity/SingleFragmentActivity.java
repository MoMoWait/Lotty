package cn.edu.fjnu.stockhelp.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import cn.edu.fjnu.stockhelp.R;

/**
 * Created by Administrator on 2017\9\2 0002.
 * 单个Fragment的Activity
 */

public abstract  class SingleFragmentActivity extends AppBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, createFragment()).commit();
    }

    public abstract Fragment createFragment();
}
