package fjnu.edu.cn.xjsscttjh.base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;

import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.data.ConstData;
import fjnu.edu.cn.xjsscttjh.view.TitleView;
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

    public float getDefaultTitleSize(){
        Toolbar toolbar = getActivity().findViewById(R.id.action_bar);
        AppCompatTextView textTitle = (AppCompatTextView) toolbar.getChildAt(0);
        return textTitle.getTextSize();
    }

    /**
     * 初始化导航栏
     */
    public void initActionBar(){
        if(ConstData.IS_TITLE_CENTER_DISPLAY){
            ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
            if(actionBar != null && actionBar.getCustomView() == null){
                actionBar.setDisplayShowCustomEnabled(true);
                TitleView titleView = new TitleView(getContext());
                actionBar.setCustomView(titleView);
                //不属于主Activity的Fragment才需要设置标题
                if( !Arrays.asList(ConstData.CONTENT_FRAGMENTS).contains(getClass())){
                    titleView.setCenterTitle(getActivityLable());
                }
            }
        }

    }

    @Override
    public void init() {
        initActionBar();
    }

    /**
     * 获取Activity的标签
     * @return
     */
    public CharSequence getActivityLable(){
        String activityLabel = getActivity().getIntent().getStringExtra(ConstData.IntentKey.TARGET_ACTIVITY_LABEL);
        if(activityLabel != null)
            return activityLabel;
        try {
            PackageManager pm = getContext().getPackageManager();
            ActivityInfo activityInfo = pm.getActivityInfo(getActivity().getComponentName(), 0);
            return activityInfo.loadLabel(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return  "";
    }
}
