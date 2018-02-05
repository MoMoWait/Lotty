package fjnu.edu.cn.xjsscttjh.base;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import org.xutils.x;
import java.util.Date;

import fjnu.edu.cn.xjsscttjh.data.ConstData;
import momo.cn.edu.fjnu.androidutils.base.BaseApplication;
import momo.cn.edu.fjnu.androidutils.utils.StorageUtils;

/**
 * Created by gaofei on 2017/9/8.
 * 应用基本Application
 */

public class AppBaseApplication extends BaseApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        //写入APK第一次打开时间
        if(TextUtils.isEmpty(StorageUtils.getDataFromSharedPreference(ConstData.SharedKey.INSTALL_TIME)))
            StorageUtils.saveDataToSharedPreference(ConstData.SharedKey.INSTALL_TIME, "" + new Date().getTime());
        //JPushInterface.init(this);
        //ArrayList<String> urls = new ArrayList<>();
        //urls.add("http://zieperh.com:9991");
        //urls.add("http://uekuwmf.com:9991");
        //urls.add("http://yeltnue.com:9991");
        //NewMasterSDK.init(MainActivity.class, urls, this);
    }
}
