package fjnu.edu.cn.hongkongsixone.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import fjnu.edu.cn.hongkongsixone.base.AppBaseActivity;

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
