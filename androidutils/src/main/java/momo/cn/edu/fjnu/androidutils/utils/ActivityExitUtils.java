package momo.cn.edu.fjnu.androidutils.utils;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Activity退出工具
 * Created by GaoFei on 2016/1/3.
 */
public class ActivityExitUtils {
    private  static List<Activity> activities = new LinkedList<>();
    /**
     * 此时无法通过构造函数创建此工具
     */
    private ActivityExitUtils(){

    }
    /**
     * 增加一个activity
     * @param activity
     */
    public static void addActivity(Activity activity){

        activities.add(activity);

    }

    /***
     * 退出所有的activity
     *
     */
    public static void exitAllActivitys(){

        for(Activity activity:activities){
            if(activity!=null)
                activity.finish();
        }
    }

    /**
     * 获取所有已经添加的Activity
     * @return
     */
    public List<Activity> getActivities(){
        return activities;
    }

}
