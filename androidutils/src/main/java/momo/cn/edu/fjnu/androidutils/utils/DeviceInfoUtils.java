package momo.cn.edu.fjnu.androidutils.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

import momo.cn.edu.fjnu.androidutils.pojo.DeviceInfo;

/**
 * 设备信息获取工具
 * Created by GaoFei on 2016/1/3.
 */
public class DeviceInfoUtils {
    private DeviceInfoUtils(){

    }

    /**获取设备信息*/
    public static DeviceInfo getDeviceInfo(Context context){
        DeviceInfo deviceInfo=new DeviceInfo();
        deviceInfo.setDenstity(getDenstity(context));
        deviceInfo.setDenstityDP(getDenstityDpi(context));
        deviceInfo.setDeviceBrand(getDeviceBrand());
        deviceInfo.setDeviceID(getDeviceID(context));
        deviceInfo.setScreenHeight(getScreenHeight(context));
        deviceInfo.setScreenWidth(getScreenWidth(context));
        deviceInfo.setScaleDenstity(getScaleDenstity(context));
        return deviceInfo;
    }
    /**
     * 获取屏幕宽度(以pix为单位)
     */
    public static int getScreenWidth(Context context){
        DisplayMetrics dm=new DisplayMetrics();
        WindowManager windowManager=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
    /**
     * 获取屏幕宽度（以dp为单位）
     */
    public static float getScreenDpWidth(Context context){
        DisplayMetrics dm=new DisplayMetrics();
        WindowManager windowManager=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.xdpi;
    }

    /**
     * 获取屏幕高度(以pix为单位)
     */
    public static int getScreenHeight(Context context){
        DisplayMetrics dm=new DisplayMetrics();
        WindowManager windowManager=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
    /**
     * 获取屏幕高度（以dp为单位）
     */
    public static float getScreenDpHeight(Context context){
        DisplayMetrics dm=new DisplayMetrics();
        WindowManager windowManager=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.ydpi;
    }
    /**
     * 获取屏幕密度(一个dp等于多少pix)
     */
    public static float getDenstity(Context context){
        DisplayMetrics dm=new DisplayMetrics();
        WindowManager windowManager=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.density;
    }
    /**
     * 获取屏幕密度(一英寸等于多少dp)
     */
    public static int getDenstityDpi(Context context){
        DisplayMetrics dm=new DisplayMetrics();
        WindowManager windowManager=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.densityDpi;
    }

    /**获取设备ID*/
    public static String getDeviceID(Context context){
        TelephonyManager telephonyManager=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }
    /**获取设备型号*/
    public static String getDeviceBrand(){
        //TelephonyManager telephonyManager=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        return android.os.Build.MODEL;
    }

    public static float getScaleDenstity(Context context){
        DisplayMetrics dm=new DisplayMetrics();
        WindowManager windowManager=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.scaledDensity;
    }

    /**
     * 获取状态栏高度
     * @param activity
     * @return  如果返回的是Integer.Min_VALUE，表示获取失败
     */
    public static int getStatusBarHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusHeight = frame.top;
        if (statusHeight == 0) {
            Class<?> c;
            Object object;
            Field field;
            int x = 0;
            statusHeight = Integer.MIN_VALUE;
            try {
                c = Class.forName("com.android.internal.R$dimen");
                object = c.newInstance();
                field = c.getField("status_bar_height");
                x = Integer.parseInt(field.get(object).toString());
                statusHeight = activity.getResources().getDimensionPixelSize(x);

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return statusHeight;
    }
}
