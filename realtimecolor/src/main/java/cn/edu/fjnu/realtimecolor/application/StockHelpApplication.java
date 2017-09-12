package cn.edu.fjnu.realtimecolor.application;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;
import momo.cn.edu.fjnu.androidutils.base.BaseApplication;

/**
 * Created by Administrator on 2017\9\2 0002.
 * 应用实例,注册推送接口等
 */

public class StockHelpApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //JPushInterface.setDebugMode(true);
        //Toast.makeText(this, "初始化推送接口", Toast.LENGTH_SHORT).show();
        JPushInterface.init(this);
        x.Ext.init(this);
    }
}
