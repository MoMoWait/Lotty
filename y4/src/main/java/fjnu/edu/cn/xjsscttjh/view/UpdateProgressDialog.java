package fjnu.edu.cn.xjsscttjh.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.activity.MainActivity;
import fjnu.edu.cn.xjsscttjh.data.ConstData;
import fjnu.edu.cn.xjsscttjh.task.ApkDownloadProgressTask;
import momo.cn.edu.fjnu.androidutils.utils.ToastUtils;

/**
 * Created by GaoFei on 2018/1/27.
 * 更新提示对话框
 */

public class UpdateProgressDialog extends Dialog {
    @ViewInject(R.id.progressBar)
    private ProgressBar mProgressBar;
    @ViewInject(R.id.text_progress)
    private TextView mTextProgress;
    private ApkDownloadProgressTask mDownloadTask;
    private Activity mActivity;
    public UpdateProgressDialog(@NonNull Context context) {
        super(context);
        mActivity = (Activity)context;
    }

/*    public UpdateProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected UpdateProgressDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //屏蔽back键
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setContentView(R.layout.dialog_update_progress);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        x.view().inject(this, getWindow().getDecorView());
        mProgressBar.setMax(100);
        mDownloadTask = new ApkDownloadProgressTask(new ApkDownloadProgressTask.Callback() {
            @Override
            public void onProgressUpdate(int progress) {
                mProgressBar.setProgress(progress);
                mTextProgress.setText("" + progress + "%");
            }

            @Override
            public void onResult(int res) {
                if(res == ConstData.TaskResult.SUCC){
                    dismiss();
                    //下载成功，启动安装
                    Intent installIntent = new Intent(Intent.ACTION_VIEW);
                    installIntent.setDataAndType(Uri.fromFile(new File(ConstData.LOCAL_LOTTY_APK_PATH)), "application/vnd.android.package-archive");
                    installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getContext().startActivity(installIntent);
                    if(mActivity != null)
                        mActivity.finish();
                }else{
                    if(mActivity != null){
                        ToastUtils.showToast("下载失败");
                        mActivity.startActivity(new Intent(mActivity, MainActivity.class));
                        mActivity.finish();
                    }
                }
            }
        });
        mDownloadTask.execute(ConstData.LOTTY_APK_URL);
    }

    @Override
    public void onDetachedFromWindow() {
        if(mDownloadTask != null && mDownloadTask.getStatus() == AsyncTask.Status.RUNNING)
            mDownloadTask.cancel(true);
    }
}
