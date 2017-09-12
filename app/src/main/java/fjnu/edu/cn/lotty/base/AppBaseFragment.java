package fjnu.edu.cn.lotty.base;

import android.support.v4.view.ViewGroupCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;

import fjnu.edu.cn.lotty.R;
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
}
