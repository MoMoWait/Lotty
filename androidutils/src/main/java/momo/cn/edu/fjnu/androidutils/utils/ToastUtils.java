package momo.cn.edu.fjnu.androidutils.utils;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import momo.cn.edu.fjnu.androidutils.data.CommonValues;

/**
 * Toast提示工具
 * Created by GaoFei on 2016/1/3.
 */
public class ToastUtils {
    private static Toast sToast;
    /**
     * 显示Toast信息
     * @param content   Toast显示的内容
     * @param textSize  显示的文字大小
     */
    public static void showToast(String content,float textSize){
        Toast toast = Toast.makeText(CommonValues.application, content, Toast.LENGTH_SHORT);
        toast.setText(content);
        LinearLayout toastLayout = (LinearLayout) toast.getView();
        TextView toastTV = (TextView) toastLayout.getChildAt(0);
        toastTV.setTextSize(textSize);
        toast.show();
    }

    /**
     * 显示Toast信息
     * @param content   Toast显示的内容
     * @param textSize  显示的文字大小
     * @param textColor 文字颜色
     */
    public static void showToast(String content,float textSize,int textColor){
        Toast toast = Toast.makeText(CommonValues.application, content, Toast.LENGTH_SHORT);
        toast.setText(content);
        LinearLayout toastLayout = (LinearLayout) toast.getView();
        TextView toastTV = (TextView) toastLayout.getChildAt(0);
        toastTV.setTextSize(textSize);
        toastTV.setTextColor(textColor);
        toast.show();
    }


    /**
     * 显示默认的Toast消息
     * @param content 显示的字符内容
     */
    public static void showToast(String content){
        if(sToast == null)
            sToast = Toast.makeText(CommonValues.application, "", Toast.LENGTH_SHORT);
        sToast.setText(content);
        sToast.show();
    }

    /**
     *显示Toast信息
     * @param content 显示的文字内容
     * @param time    显示时间
     */
    public static void showToast(String content,int time){
        Toast.makeText(CommonValues.application, content, time).show();
    }


    /**
     * 显示Toast消息
     * @param customView 自定义视图
     */
    public static void showToast(View customView){
        showToast(customView, Toast.LENGTH_SHORT);
    }


    /**
     * 显示Toast信息
     * @param customView 自定义视图
     * @param time       显示时间
     */
    public static void showToast(View customView, int time){
        Toast toast = new Toast(CommonValues.application);
        toast.setDuration(time);
        toast.setView(customView);
        toast.show();
    }
}
