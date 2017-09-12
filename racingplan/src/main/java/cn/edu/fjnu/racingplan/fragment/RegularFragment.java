package cn.edu.fjnu.racingplan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import cn.edu.fjnu.racingplan.activity.BrowserActivity;
import cn.edu.fjnu.racingplan.data.ConstData;

/**
 * Created by Administrator on 2017\9\2 0002.
 * 规律分析模块
 */

public class RegularFragment extends ListFragment {
    public static final String[] HEADERS = new String[]{"历史同期", "组选遗漏", "振幅走势"};
    public static final String[] URLS = {
            "http://chart.zhcw.com/index/?act=lstq",
            "http://chart.zhcw.com/3d/3d_zxyl_50.html",
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
