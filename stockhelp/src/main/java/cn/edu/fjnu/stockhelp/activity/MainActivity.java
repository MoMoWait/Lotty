package cn.edu.fjnu.stockhelp.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.AlertDialog;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.edu.fjnu.stockhelp.R;
import cn.edu.fjnu.stockhelp.adapter.TabAdapter;

@ContentView(R.layout.activity_main)
public class MainActivity extends FragmentActivity {

    @ViewInject(R.id.pager_content)
    private ViewPager mContentPager;
    @ViewInject(R.id.text_chart)
    private TextView mTextChart;
    @ViewInject(R.id.text_regular)
    private TextView mTextRegular;
    @ViewInject(R.id.text_information)
    private TextView mTextInformation;
    @ViewInject(R.id.text_my_info)
    private TextView mTextMyInfo;
    @ViewInject(R.id.layout_tab)
    private LinearLayout mLayouTab;
    private int mCurrSelectIndex = 0;
    private TextView[] mTextTabs = new TextView[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        x.view().inject(this);
        init();
    }

    private void init(){
        mTextTabs[0] = mTextChart;
        mTextTabs[1] = mTextRegular;
        mTextTabs[2] = mTextInformation;
        mTextTabs[3] = mTextMyInfo;
        for(TextView itemTextTab : mTextTabs){
            itemTextTab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContentPager.setCurrentItem(mLayouTab.indexOfChild(view), true);
                }
            });
        }
        mContentPager.setAdapter(new TabAdapter(getSupportFragmentManager()));
        mContentPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //更新底部的颜色
                for(int i = 0; i < mTextTabs.length; ++i){
                    if(i != position)
                        mTextTabs[i].setBackgroundColor(getResources().getColor(R.color.black));
                    else
                        mTextTabs[i].setBackgroundColor(getResources().getColor(R.color.gray));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            new AlertDialog.Builder(this).setTitle("温馨提示").setCancelable(false).setMessage("确认退出?")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
