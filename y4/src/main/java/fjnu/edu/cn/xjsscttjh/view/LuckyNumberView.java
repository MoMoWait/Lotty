package fjnu.edu.cn.xjsscttjh.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import momo.cn.edu.fjnu.androidutils.utils.SizeUtils;

/**
 * Created by gaofei on 2017/12/3.
 * 中奖号
 */

public class LuckyNumberView extends AppCompatTextView {
    private Paint mPaint = new Paint();
    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public LuckyNumberView(Context context) {
        super(context);
        init();
    }

    public LuckyNumberView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LuckyNumberView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(SizeUtils.dp2px(40), SizeUtils.dp2px(40));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(SizeUtils.dp2px(40) / 2, SizeUtils.dp2px(40) / 2, SizeUtils.dp2px(40) / 2, mPaint);
        canvas.drawText("1", SizeUtils.dp2px(20), SizeUtils.dp2px(20), mTextPaint);
    }

    private void init(){
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mTextPaint.setTextSize(SizeUtils.px2sp(SizeUtils.dp2px(100)));
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStrokeWidth(5);
    }
}
