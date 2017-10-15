package fjnu.edu.cn.bjk3js.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import fjnu.edu.cn.bjk3js.base.AppBaseActivity;

/**
 * Created by gaofei on 2017/9/8.
 * 单个Fragment的Activity
 */

public  abstract class SingleFragmentActivity extends AppBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, createFragment()).commit();
    }

    public abstract Fragment createFragment();
}
