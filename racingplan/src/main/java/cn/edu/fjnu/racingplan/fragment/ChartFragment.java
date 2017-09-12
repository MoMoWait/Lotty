package cn.edu.fjnu.racingplan.fragment;;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xutils.view.annotation.ContentView;
import java.util.List;
import cn.edu.fjnu.racingplan.R;
import cn.edu.fjnu.racingplan.adapter.ColorAdapter;
import cn.edu.fjnu.racingplan.model.ColorInfoLoadTask;
import cn.edu.fjnu.racingplan.pojo.ColorInfo;
import momo.cn.edu.fjnu.androidutils.utils.SizeUtils;

/**
 * Created by Administrator on 2017\9\2 0002.
 * 开奖模块
 */

@ContentView(R.layout.fragment_chart)
public class ChartFragment extends ListFragment {

    private ColorInfoLoadTask mLoadTask;
    private ListView mListView;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public void initData() {
        mListView = getListView();
        mListView.setPadding(0, SizeUtils.dp2px(10), 0, 0);
        mListView.setDividerHeight(SizeUtils.dp2px(20));
        //加载数据
        mLoadTask = new ColorInfoLoadTask(new ColorInfoLoadTask.Callback() {
            @Override
            public void onGetResult(List<ColorInfo> colorInfos) {
                if(colorInfos != null){
                    ColorAdapter colorAdapter = new ColorAdapter(getActivity(), R.layout.adapter_color, 0, colorInfos);
                    setListAdapter(colorAdapter);
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
