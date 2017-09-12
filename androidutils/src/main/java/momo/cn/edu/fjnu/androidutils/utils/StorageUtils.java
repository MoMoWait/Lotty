package momo.cn.edu.fjnu.androidutils.utils;

import android.content.Context;
import android.content.SharedPreferences;

import momo.cn.edu.fjnu.androidutils.data.CommonValues;

/**
 * 存储工具
 * Created by GaoFei on 2016/1/4.
 */
public class StorageUtils {

    /**
     * 将数据存储在SharedPreference
     * @param key
     * @param value
     */
    public static void saveDataToSharedPreference(String key, String value){
        SharedPreferences preferences = CommonValues.application.getSharedPreferences(PackageUtils.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 从SharedPreference中读取数据
     * @param key
     * @return
     */
    public static String getDataFromSharedPreference(String key){
        SharedPreferences preferences = CommonValues.application.getSharedPreferences(PackageUtils.getPackageName(), Context.MODE_PRIVATE);
        return preferences.getString(key ,"");
    }


}
