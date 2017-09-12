package momo.cn.edu.fjnu.androidutils.utils;

import android.content.Context;

import momo.cn.edu.fjnu.androidutils.view.LoadingDialog;

/**
 * 对话框工具
 * Created by GaoFei on 2016/3/9.
 */
public class DialogUtils {
    private DialogUtils(){

    }
    /**载入对话框*/
    private static LoadingDialog mLoadingDialog;
    /**显示载入对话框*/
    public static void showLoadingDialog(Context context,boolean isCancelable) {

        mLoadingDialog = new LoadingDialog(context,isCancelable);
        mLoadingDialog.show();
    }
    /**关闭载入对话框*/
    public static void closeLoadingDialog() {

        if (mLoadingDialog != null && mLoadingDialog.isShowing())
            mLoadingDialog.dismiss();
    }


}
