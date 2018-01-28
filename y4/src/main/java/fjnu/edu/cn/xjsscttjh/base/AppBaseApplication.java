package fjnu.edu.cn.xjsscttjh.base;

import android.text.TextUtils;

import com.mastersdk.android.NewMasterSDK;

import org.xutils.x;

//import cn.jpush.android.api.JPushInterface;
import java.util.ArrayList;
import java.util.Date;

import fjnu.edu.cn.xjsscttjh.activity.MainActivity;
import fjnu.edu.cn.xjsscttjh.data.ConstData;
import momo.cn.edu.fjnu.androidutils.base.BaseApplication;
import momo.cn.edu.fjnu.androidutils.utils.StorageUtils;

/**
 * Created by gaofei on 2017/9/8.
 * 应用基本Application
 */

public class AppBaseApplication extends BaseApplication {
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
