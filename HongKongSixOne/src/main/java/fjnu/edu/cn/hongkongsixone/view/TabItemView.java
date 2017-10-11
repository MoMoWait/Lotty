package fjnu.edu.cn.hongkongsixone.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import fjnu.edu.cn.hongkongsixone.R;
import fjnu.edu.cn.hongkongsixone.data.ConstData;
import momo.cn.edu.fjnu.androidutils.utils.SizeUtils;

/**
 * Created by gaofei on 2017/9/10.
 * 底部导航栏项
 */

public class TabItemView extends LinearLayout {

    private Context mContext;
    @ViewInject(R.id.img_bottom)
    private ImageView mImgBottom;
    @ViewInject(R.id.text_des)
    private TextView mTextDes;
    @ViewInject(R.id.layout_container)
    private LinearLayout mLayoutContainer;
    public TabItemView(Context context) {
        super(context);
        init(context);
    }

    public TabItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TabItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public TabItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        mContext = context;
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER);
        inflate(mContext, R.layout.view_tab_item, this);
        x.view().inject(this);
        hideText();
    }

    public void setImgText(int bottomImg, String textDes){
        mImgBottom.setImageResource(bottomImg);
        mTextDes.setText(textDes);
    }

    public void setDesTextColor(int color){
        mTextDes.setTextColor(color);
    }

    public void setBottomImg(int resImg){
        mImgBottom.setImageResource(resImg);
    }

    /**
     * 隐藏文字
     */
    private void hideText(){
        if(!ConstData.IS_SHOW_TAB_TEXT){
            mTextDes.setVisibility(View.GONE);
            mImgBottom.setScaleType(ImageView.ScaleType.FIT_CENTER);
            mLayoutContainer.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams imgParams = (LinearLayout.LayoutParams)mImgBottom.getLayoutParams();
            imgParams.width = SizeUtils.dp2px(60);
            imgParams.height = SizeUtils.dp2px(60);
            mImgBottom.setLayoutParams(imgParams);
        }

    }
}
