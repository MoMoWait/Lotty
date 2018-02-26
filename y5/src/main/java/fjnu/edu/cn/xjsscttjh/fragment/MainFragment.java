package fjnu.edu.cn.xjsscttjh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.adapter.TabAdapter;
import fjnu.edu.cn.xjsscttjh.base.AppBaseFragment;
import fjnu.edu.cn.xjsscttjh.data.ConstData;
import fjnu.edu.cn.xjsscttjh.view.LoginActionBarView;
import fjnu.edu.cn.xjsscttjh.view.TabItemView;
import fjnu.edu.cn.xjsscttjh.view.TitleView;

/**
 * Created by gaofei on 2017/9/9.
 * 主页面
 */
@ContentView(R.layout.fragment_main)
public class MainFragment extends AppBaseFragment{
    private static final String TAG = "MainFragment";
    @ViewInject(R.id.pager_content)
    private ViewPager mPagerContent;
    @ViewInject(R.id.layout_tab)
    private LinearLayout mLayoutTab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onResume() {
        super.onResume();
        //setActionBarCenterText(getString(R.string.app_name));
    }

    @Override
    public void init() {
        super.init();
        loadBottomUI();
        mPagerContent.setAdapter(new TabAdapter(getActivity().getSupportFragmentManager()));
        updateBottomUI(mPagerContent.getCurrentItem());
        updateTitleView(mPagerContent.getCurrentItem());
        mPagerContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //更新导航栏图标，颜色
                updateBottomUI(position);
                updateTitleView(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //添加底部导航栏的响应事件
        for(int i = 0; i < mLayoutTab.getChildCount(); ++i){
            mLayoutTab.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int itemTabPosition = mLayoutTab.indexOfChild(view);
                    //切换Fragment
                    mPagerContent.setCurrentItem(itemTabPosition, true);
                    //更新导航栏项的图标和颜色
                    updateBottomUI(itemTabPosition);
                }
            });
        }
    }


    /**
     * 加载底部菜单项
     */
    private void loadBottomUI(){
        for(int i = 0; i < ConstData.TAB_ITEMS.length; ++i){
            TabItemView tabItemView = new TabItemView(getContext());
            tabItemView.setImgText(ConstData.TAB_ITEMS[i].getNormalImg(), ConstData.TAB_ITEMS[i].getTitle());
            LinearLayout.LayoutParams tabItemParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            mLayoutTab.addView(tabItemView, tabItemParams);
        }
    }

    /**
     * 更新底部导航栏项的图标和颜色
     * @param position
     */
    private void updateBottomUI(int position){
        for(int i = 0; i < mLayoutTab.getChildCount(); ++i){
            TabItemView tabItemView = (TabItemView) mLayoutTab.getChildAt(i);
            if(i == position){
                tabItemView.setBottomImg(ConstData.TAB_ITEMS[i].getSelectImg());
                tabItemView.setDesTextColor(ConstData.TAB_ITEMS[i].getSelectTextColor());
            }else{
                tabItemView.setBottomImg(ConstData.TAB_ITEMS[i].getNormalImg());
                tabItemView.setDesTextColor(ConstData.TAB_ITEMS[i].getNormalTextColor());
            }
        }
    }

    /**
     * 更新标题视图
     * @param position
     */
    private void updateTitleView(int position){
        if(ConstData.IS_TITLE_CENTER_DISPLAY){
            ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
            if(actionBar != null){
                TitleView titleView = (TitleView) actionBar.getCustomView();
                titleView.setCenterTitle(ConstData.TAB_ITEMS[position].getTitle());
            }
        }
    }

}
