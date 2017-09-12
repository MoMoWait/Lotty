package fjnu.edu.cn.lotty.base;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;
import momo.cn.edu.fjnu.androidutils.base.BaseApplication;

/**
 * Created by gaofei on 2017/9/8.
 * 应用基本Application
 */

public class AppBaseApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        JPushInterface.init(this);
    }
}
