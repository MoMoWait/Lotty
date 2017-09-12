package cn.edu.fjnu.stockhelp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.edu.fjnu.stockhelp.fragment.ChartFragment;
import cn.edu.fjnu.stockhelp.fragment.InformationFragment;
import cn.edu.fjnu.stockhelp.fragment.MyInfoFragment;
import cn.edu.fjnu.stockhelp.fragment.RegularFragment;

/**
 * 切换Fragmen的适配器
 * Created by GaoFei on 2016/5/8.
 */
public class TabAdapter extends FragmentPagerAdapter{

    private Class<?>[] contentFragments = new Class[]{ChartFragment.class, RegularFragment.class, InformationFragment.class, MyInfoFragment.class};
    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        try {
            return (Fragment) contentFragments[position].newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getCount() {
        return contentFragments.length;
    }
}