package cn.edu.fjnu.stockhelp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.net.URL;

import cn.edu.fjnu.stockhelp.activity.BrowserActivity;
import cn.edu.fjnu.stockhelp.data.ConstData;

/**
 * Created by Administrator on 2017\9\2 0002.
 * 图表分析模块
 */

public class ChartFragment extends ListFragment {

    public static final String[] HEADERS = new String[]{"综合", "组选分布", "012路分布", "大小形态"
    , "基偶形态", "质和形态", "直选百位", "直选十位", "直选个位", "和值分布", "和伪分布", "跨度走势",
    "历史同期号", "试机号跟随", "试机号走势", "组选遗漏", "组选间距", "振幅走势", "排序号走势"};
    public static final String[] URLS = {
            "http://chart.zhcw.com/3d/3d_zhfb_50.html",
            "http://chart.zhcw.com/3d/3d_zxfb_50.html",
            "http://chart.zhcw.com/3d/3d_zx012_50.html",
            "http://chart.zhcw.com/3d/3d_dxxt_50.html",
            "http://chart.zhcw.com/3d/3d_joxt_50.html",
            "http://chart.zhcw.com/3d/3d_zhxt_50.html",
            "http://chart.zhcw.com/3d/3d_dxbw_50.html",
            "http://chart.zhcw.com/3d/3d_dxbw_50.html",
            "http://chart.zhcw.com/3d/3d_dxgw_50.html",
            "http://chart.zhcw.com/3d/3d_hzfb_50.html",
            "http://chart.zhcw.com/3d/3d_hwfb_50.html",
            "http://chart.zhcw.com/3d/3d_kdzs_50.html",
            "http://chart.zhcw.com/index/?act=lstq",
            "http://chart.zhcw.com/3d/3d_sjhgs_50.html",
            "http://chart.zhcw.com/3d/3d_sjhzs_50.html",
            "http://chart.zhcw.com/3d/3d_zxyl_50.html",
            "http://chart.zhcw.com/3d/3d_zxjj_50.html",
            "http://chart.zhcw.com/3d/3d_zfzs_50.html",
            "http://chart.zhcw.com/3d/3d_pxhzs_50.html"
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, HEADERS);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), BrowserActivity.class);
                intent.putExtra(ConstData.IntentKey.WEB_LOAD_URL, URLS[i]);
                startActivity(intent);

            }
        });
    }
}
