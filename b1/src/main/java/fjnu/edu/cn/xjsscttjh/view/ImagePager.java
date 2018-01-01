package fjnu.edu.cn.xjsscttjh.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by GaoFei on 2017/12/31.
 * 自定义的ViewPager
 */

public class ImagePager extends ViewPager {
    public ImagePager(Context context) {
        super(context);
    }

    public ImagePager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
