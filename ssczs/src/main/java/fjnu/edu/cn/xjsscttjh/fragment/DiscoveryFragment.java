package fjnu.edu.cn.xjsscttjh.fragment;

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

import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.activity.ChartActivity;
import fjnu.edu.cn.xjsscttjh.base.AppBaseFragment;
import fjnu.edu.cn.xjsscttjh.data.ConstData;

/**
 * Created by gaofei on 2017/9/10.
 * 发现页面
 */

@ContentView(R.layout.fragment_discovery)
public class DiscoveryFragment extends AppBaseFragment {
    @ViewInject(R.id.list_chart)
    private ListView mListChart;

    public static final String[] TITLES =  new String[]{"基本走势", "定位走势", "龙虎走势", "冠亚走势", "冠亚和值", "大小形态",
            "奇偶形态", "质和形态", "跨度走势", "尾数走势", "五行走势", "除三余(012)走势"};

    public static final String[] URLS = new String[]{
            "http://kj.13322.com/pk10_BaseTrend_30.html", "http://kj.13322.com/pk10_PositionTrend.html", "http://kj.13322.com/pk10_DragonTigerTrend.html",
            "http://kj.13322.com/pk10_FirstSecondTrend.html", "http://kj.13322.com/pk10_FirstSecondSumTrend.html", "http://kj.13322.com/pk10_BigSmallTrend.html",
            "http://kj.13322.com/pk10_OddEvenTrend.html", "http://kj.13322.com/pk10_PriComTrend.html", "http://kj.13322.com/pk10_SpanTrend.html",
            "http://kj.13322.com/pk10_SumMantissaTrend.html", "http://kj.13322.com/pk10_WuXingTrend.html", "http://kj.13322.com/pk10_Mod3Trend.html"};

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
