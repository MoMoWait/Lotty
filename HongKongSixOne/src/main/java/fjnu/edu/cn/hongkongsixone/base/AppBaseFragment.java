package fjnu.edu.cn.hongkongsixone.base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import fjnu.edu.cn.hongkongsixone.R;
import momo.cn.edu.fjnu.androidutils.base.BaseFragment;

/**
 * Created by gaofei on 2017/9/8.
 *  应用基本
 */

public abstract class AppBaseFragment extends BaseFragment {
    private static final String TAG = "AppBaseFragment";
    /**
     * 设置中间文字
     */
    public void setActionBarCenterText(String text){
        Toolbar toolbar = getActivity().findViewById(R.id.action_bar);
        AppCompatTextView textTitle = (AppCompatTextView) toolbar.getChildAt(0);
        textTitle.setText(text);
        ViewGroup.LayoutParams params = textTitle.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        textTitle.setGravity(Gravity.CENTER);
        textTitle.setLayoutParams(params);

    }

    /**
     * 使中间标题栏居中
     */
    public void makeActionBarCenterText(){
        Toolbar toolbar = getActivity().findViewById(R.id.action_bar);
        AppCompatTextView textTitle = (AppCompatTextView) toolbar.getChildAt(0);
        ViewGroup.LayoutParams params = textTitle.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        textTitle.setGravity(Gravity.CENTER);
        textTitle.setLayoutParams(params);
    }

    /**
     * 添加右侧文字
     */
    public TextView addActionBarRightText(String text){
        Toolbar toolbar = getActivity().findViewById(R.id.action_bar);
        AppCompatTextView textTitle = (AppCompatTextView) toolbar.getChildAt(0);
        float titleSize = textTitle.getTextSize();
        int titleColor = textTitle.getCurrentTextColor();
        TextView rightTextView = new TextView(getContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rightTextView.setLayoutParams(params);
        rightTextView.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
        rightTextView.setTextSize(titleSize * 0.8f);
        rightTextView.setTextColor(titleColor);
        rightTextView.setText(text);
        toolbar.addView(rightTextView);
        return rightTextView;
    }

    /**
     * 移除右侧文字
     * @param rightTextView
     */
    public void removeActionBarText(TextView rightTextView){
        Toolbar toolbar = getActivity().findViewById(R.id.action_bar);
        toolbar.removeView(rightTextView);
    }

    public void showNetWorkErrorDialog(){
        Log.i(TAG, "showNetWorkErrorDialog->logs:" + Log.getStackTraceString(new Throwable()));
        if(getActivity() != null){
            new AlertDialog.Builder(getContext()).setCancelable(false).setTitle("温馨提示")
                    .setMessage("请检查网络").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    getActivity().finish();
                }
            }).show();
        }

    }
}
