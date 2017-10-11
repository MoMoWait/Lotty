package fjnu.edu.cn.hongkongsixtwo.fragment;

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

import fjnu.edu.cn.hongkongsixtwo.R;
import fjnu.edu.cn.hongkongsixtwo.activity.BrowserActivity;
import fjnu.edu.cn.hongkongsixtwo.activity.ChartActivity;
import fjnu.edu.cn.hongkongsixtwo.base.AppBaseFragment;
import fjnu.edu.cn.hongkongsixtwo.data.ConstData;

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


    public static final String[] TITLES =  new String[]{"5星走势", "5星和值跨度", "3星走势", "3星和值跨度",
    "3星大小奇偶", "2星走势", "2星和值跨度", "大小单双走势"};

    public static final String[] URLS = new String[]{
            "http://xjssc.icaile.com/?op=jb5", "http://xjssc.icaile.com/?op=hzkd5", "http://xjssc.icaile.com/?op=jb3",
            "http://xjssc.icaile.com/?op=hzkd3", "http://xjssc.icaile.com/?op=dxjo3", "http://xjssc.icaile.com/?op=jb2",
            "http://xjssc.icaile.com/?op=hzkd2", "http://xjssc.icaile.com/?op=dxds"};

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
