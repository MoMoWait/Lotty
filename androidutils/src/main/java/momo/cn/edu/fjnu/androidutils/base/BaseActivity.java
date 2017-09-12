package momo.cn.edu.fjnu.androidutils.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import momo.cn.edu.fjnu.androidutils.utils.ActivityExitUtils;

/**
 * 基本Activity
 * Created by GaoFei on 2016/1/3.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityExitUtils.addActivity(this);
    }

}
