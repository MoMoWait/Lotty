package momo.cn.edu.fjnu.androidutils.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * 基本对话框,所有的对话框都要继承此类
 * Created by GaoFei on 2016/3/7.
 */
public abstract class BaseDialog extends Dialog{

    public BaseDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //设置背景透明
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initView();
        initData();
        initEvent();
    }

    /**
     * 初始化界面
     */
    public abstract void initView();

    /**
     * c初始化数据
     */
    public abstract void initData();

    /**
     * 初始化事件
     */
    public abstract void initEvent();

}
