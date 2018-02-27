package fjnu.edu.cn.xjsscttjh.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.activity.ForcaestInfoDisplayActivity;
import fjnu.edu.cn.xjsscttjh.adapter.ForcaestInfoAdapter;
import fjnu.edu.cn.xjsscttjh.adapter.WyForecastInfoAdapter;
import fjnu.edu.cn.xjsscttjh.base.AppBaseFragment;
import fjnu.edu.cn.xjsscttjh.bean.ForecastInfo;
import fjnu.edu.cn.xjsscttjh.data.ConstData;
import fjnu.edu.cn.xjsscttjh.utils.LottyDataGetUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import momo.cn.edu.fjnu.androidutils.utils.ToastUtils;

/**
 * Created by gaofei on 2018/2/4.
 * 彩票预测消息页面
 */

@ContentView(R.layout.fragment_forcaestinfo)
public class ForcaestInfoFragment extends AppBaseFragment{
    @ViewInject(R.id.list_forcaest_info)
    private ListView mListForcaestInfo;
    @ViewInject(R.id.progress_load)
    private ProgressBar mProgressLoad;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void init() {
        super.init();
    }

    /*
    @Override
    public void initActionBar() {
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        Set<String> lottyTitles = ConstData.WY_LOTTY_FORECAST_URLS.keySet();
        for(String itemTitle : lottyTitles){
            ActionBar.Tab itemTab = actionBar.newTab();
            itemTab.setText(itemTitle);
            itemTab.setTag(itemTitle);
            itemTab.setTabListener(new ActionBar.TabListener() {
                @Override
                public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                    loadData(tab.getText().toString());
                    //ToastUtils.showToast("select:" + tab.getTag());
                }

                @Override
                public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

                }

                @Override
                public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

                }
            });
            actionBar.addTab(itemTab);
            //actionBar.setSelectedNavigationItem(0);
        }
    }*/

    private void loadData(final String title){
        mProgressLoad.setVisibility(View.VISIBLE);
        mListForcaestInfo.setVisibility(View.GONE);
        Observable.create(new ObservableOnSubscribe<List<ForecastInfo>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ForecastInfo>> e) throws Exception {
                e.onNext(LottyDataGetUtils.getForcaestInfosByWy(ConstData.WY_LOTTY_FORECAST_URLS.get(title)));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<ForecastInfo>>() {
            @Override
            public void accept(final List<ForecastInfo> colorInfos) throws Exception {
                mProgressLoad.setVisibility(View.GONE);
                mListForcaestInfo.setVisibility(View.VISIBLE);
                WyForecastInfoAdapter adapter = new WyForecastInfoAdapter(getContext(), R.layout.adapter_forcaest_info, colorInfos);
                mListForcaestInfo.setAdapter(adapter);
                mListForcaestInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ForecastInfo forecastInfo = (ForecastInfo) colorInfos.get(position);
                        Intent intent = new Intent(getContext(), ForcaestInfoDisplayActivity.class);
                        intent.putExtra(ConstData.IntentKey.WEB_LOAD_TITLE, forecastInfo.getTitle());
                        intent.putExtra(ConstData.IntentKey.WEB_LOAD_TIME, forecastInfo.getTime());
                        intent.putExtra(ConstData.IntentKey.WEB_LOAD_URL, forecastInfo.getUrl());
                        startActivity(intent);
                    }
                });
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mProgressLoad.setVisibility(View.GONE);
                ToastUtils.showToast("发生异常，请重试");
            }
        });
    }
}
