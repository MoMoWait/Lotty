package fjnu.edu.cn.bjk3js.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fjnu.edu.cn.bjk3js.data.ConstData;

/**
 * 切换Fragmen的适配器
 * Created by GaoFei on 2016/5/8.
 */
public class TabAdapter extends FragmentPagerAdapter{

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        try {
            return (Fragment) ConstData.CONTENT_FRAGMENTS[position].newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getCount() {
        return ConstData.CONTENT_FRAGMENTS.length;
    }
}