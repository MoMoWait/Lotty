package fjnu.edu.cn.bjk3js.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import fjnu.edu.cn.bjk3js.activity.RegisterActivity;
import fjnu.edu.cn.bjk3js.R;

/**
 * Created by Administrator on 2017\9\14 0014.
 * 自定义的登录页面的ActionBar控件
 */

public class LoginActionBarView extends RelativeLayout {
    private Context mContext;

    @ViewInject(R.id.text_register)
    private TextView mTextRegister;

    public LoginActionBarView(Context context) {
        super(context);
        init(context);
    }

    public LoginActionBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoginActionBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public LoginActionBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        mContext = context;
        inflate(mContext, R.layout.view_actionbar_login, this);
        x.view().inject(this);
        mTextRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, RegisterActivity.class));
            }
        });
    }
}
