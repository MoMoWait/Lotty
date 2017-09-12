package momo.cn.edu.fjnu.androidutils.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import momo.cn.edu.fjnu.androidutils.data.CommonValues;

/**
 * 尺寸转换工具
 * Created by GaoFei on 2016/1/3.
 */
public class SizeUtils {
    private SizeUtils(){
    }

    /**
     * 将dp转化成像素
     *
     * @param dpValue
     * @return
     */
    public static int dp2px(float dpValue){
       return (int)(DeviceInfoUtils.getDenstity(CommonValues.application) * dpValue);
    }

    /**
     * 将sp转化成像素
     *
     * @param spValue
     * @return
     */
    public float sp2px(float spValue){
        return DeviceInfoUtils.getScaleDenstity(CommonValues.application) * spValue;
    }

    /**
     * 将像素转化成dp
     * @return
     */
    public float px2dp(int pxValue){
        return pxValue / DeviceInfoUtils.getDenstity(CommonValues.application);
    }

}
