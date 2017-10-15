package fjnu.edu.cn.xy28.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import fjnu.edu.cn.xy28.R;
import fjnu.edu.cn.xy28.activity.ChartActivity;
import fjnu.edu.cn.xy28.base.AppBaseFragment;
import fjnu.edu.cn.xy28.data.ConstData;

/**
 * Created by gaofei on 2017/9/10.
 * 发现页面
 */

@ContentView(R.layout.fragment_discovery)
public class DiscoveryFragment extends AppBaseFragment {
    @ViewInject(R.id.list_chart)
    private ListView mListChart;

    //public static final String[] TITLES =  new String[]{"质和形态", "和值分布", "组选遗漏"};

    //public static final String[] URLS = new String[]{
    //        "http://chart.zhcw.com/3d/3d_zhxt_50.html", "http://chart.zhcw.com/3d/3d_hzfb_50.html",
    //        "http://chart.zhcw.com/3d/3d_zxyl_50.html"};


    public static final String[] TITLES =  new String[]{"综合", "跨度走势", "和值分布"};

    public static final String[] URLS = new String[]{
            "http://chart.zhcw.com/3d/3d_zhfb_50.html", "http://chart.zhcw.com/3d/3d_kdzs_50.html", "http://chart.zhcw.com/3d/3d_hzfb_50.html"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void init() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, TITLES);
        mListChart.setAdapter(adapter);
        mListChart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ChartActivity.class);
                intent.putExtra(ConstData.IntentKey.WEB_LOAD_URL, URLS[i]);
                startActivity(intent);
            }
        });
    }
}
