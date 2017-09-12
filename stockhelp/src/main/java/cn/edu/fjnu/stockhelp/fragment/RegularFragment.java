package cn.edu.fjnu.stockhelp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import cn.edu.fjnu.stockhelp.activity.BrowserActivity;
import cn.edu.fjnu.stockhelp.data.ConstData;

/**
 * Created by Administrator on 2017\9\2 0002.
 * 规律分析模块
 */

public class RegularFragment extends ListFragment {
    public static final String[] HEADERS = new String[]{"和值表", "和尾表", "跨度表", "奇偶表"
            , "大小表", "质和表", "连号表", "012路表"};
    public static final String[] URLS = {
            "http://data.zhcw.com/h5/chart/hzb.html?from=client&src=0003000001%7C0401004100",
            "http://data.zhcw.com/h5/chart/hwb.html?from=client&src=0003000001%7C0401004100",
            "http://data.zhcw.com/h5/chart/kdb.html?from=client&src=0003000001%7C0401004100",
            "http://data.zhcw.com/h5/chart/job.html?from=client&src=0003000001%7C0401004100",
            "http://data.zhcw.com/h5/chart/dxb.html?from=client&src=0003000001%7C0401004100",
            "http://data.zhcw.com/h5/chart/zhb.html?from=client&src=0003000001%7C0401004100",
            "http://data.zhcw.com/h5/chart/lhb.html?from=client&src=0003000001%7C0401004100",
            "http://data.zhcw.com/h5/chart/lyelb.html?from=client&src=0003000001%7C0401004100"

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
