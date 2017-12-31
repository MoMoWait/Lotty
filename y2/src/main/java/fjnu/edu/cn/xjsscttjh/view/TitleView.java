package fjnu.edu.cn.xjsscttjh.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import fjnu.edu.cn.xjsscttjh.R;

/**
 * Created by Administrator on 2017/11/22.
 * 标题视图
 */

public class TitleView extends RelativeLayout {
    private Context mContext;
    @ViewInject(R.id.img_left)
    private ImageView mImgLeft;
    @ViewInject(R.id.text_title)
    private TextView mTextTitle;
    @ViewInject(R.id.text_right)
    private TextView mTextRight;
    public TitleView(Context context) {
        super(context);
        init(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void setLeftView(View leftView){

    }

    public void setRightView(View rightView){

    }

    public void setCenterTitleSize(float textSize){
        mTextTitle.setTextSize(textSize);
    }


    public void setCenterTitle(CharSequence title){
        mTextTitle.setText(title);
    }


    public void init(Context context){
        mContext = context;
        inflate(mContext, R.layout.view_title, this);
        x.view().inject(this);
    }
}
