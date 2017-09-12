package momo.cn.edu.fjnu.androidutils.base;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import momo.cn.edu.fjnu.androidutils.data.CommonValues;

/**
 * 全局应用程序基类
 * Created by GaoFei on 2016/1/3.
 */
public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("BaseApplication", "基础Application实例化");
        init();
    }

    /**
     * 初始化
     */
    public void init(){
        CommonValues.application = this;
    }
}
