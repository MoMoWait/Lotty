package cn.edu.fjnu.realtimecolor.fragment;;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import cn.edu.fjnu.realtimecolor.R;
import cn.edu.fjnu.realtimecolor.adapter.ColorAdapter;
import cn.edu.fjnu.realtimecolor.model.ColorInfoLoadTask;
import cn.edu.fjnu.realtimecolor.pojo.AllColorInfo;
import momo.cn.edu.fjnu.androidutils.base.BaseFragment;

/**
 * Created by Administrator on 2017\9\2 0002.
 * 开奖模块
 */

@ContentView(R.layout.fragment_chart)
public class ChartFragment extends BaseFragment {

    @ViewInject(R.id.list_color)
    private ListView mListColor;

    private ColorInfoLoadTask mLoadTask;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }


    @Override
    public void init() {
        initData();
    }

    public void initData() {
        //加载数据
        mLoadTask = new ColorInfoLoadTask(new ColorInfoLoadTask.Callback() {
            @Override
            public void onGetResult(List<AllColorInfo> allColorInfos) {
                if(allColorInfos != null){
                    ColorAdapter colorAdapter = new ColorAdapter(getActivity(), R.layout.adapter_color, 0, allColorInfos);
                    mListColor.setAdapter(colorAdapter);
                }
            }
        });
        mLoadTask.execute();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mLoadTask != null && mLoadTask.getStatus() == AsyncTask.Status.RUNNING)
            mLoadTask.cancel(true);
    }
}
