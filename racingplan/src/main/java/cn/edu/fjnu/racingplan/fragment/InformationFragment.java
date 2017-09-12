package cn.edu.fjnu.racingplan.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.fjnu.racingplan.activity.BrowserActivity;
import cn.edu.fjnu.racingplan.data.ConstData;
import cn.edu.fjnu.racingplan.model.InformationLoadTask;
import cn.edu.fjnu.racingplan.pojo.HotInformation;

/**
 * Created by Administrator on 2017\9\2 0002.
 * 热门资讯模块
 */

public class InformationFragment extends ListFragment {
    private InformationLoadTask mInformationLoadTask;
    private ListView mListView;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView = getListView();
        mInformationLoadTask = new InformationLoadTask(new InformationLoadTask.Callback() {
            @Override
            public void onGetResult(List<HotInformation> informations) {
                if(informations != null){
                    List<String> titles = new ArrayList<>();
                    final List<String> urls = new ArrayList<>();
                    for(HotInformation itemInformation : informations){
                        titles.add(itemInformation.getTitle());
                        urls.add(itemInformation.getUrl());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, titles);
                    setListAdapter(adapter);
                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getActivity(), BrowserActivity.class);
                            intent.putExtra(ConstData.IntentKey.WEB_LOAD_URL, urls.get(i));
                            intent.putExtra(ConstData.IntentKey.IS_INFORMATION_URL, true);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
        mInformationLoadTask.execute();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mInformationLoadTask != null && mInformationLoadTask.getStatus() == AsyncTask.Status.RUNNING)
            mInformationLoadTask.cancel(true);
    }
}
