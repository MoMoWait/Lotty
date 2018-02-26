package fjnu.edu.cn.xjsscttjh.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
        loadData();
    }

    private void loadData(){
        mProgressLoad.setVisibility(View.VISIBLE);
        Observable.create(new ObservableOnSubscribe<List<Object>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Object>> e) throws Exception {
                Map<String, List<ForecastInfo>> mInfoMap = LottyDataGetUtils.getAllForecastInfoByFC();
                List<Object> infos = new ArrayList<>();
                Set<String> titles = mInfoMap.keySet();
                for(String title : titles){
                    infos.add(title);
                    List<ForecastInfo> forecastInfos = mInfoMap.get(title);
                    infos.addAll(forecastInfos);
                }
                e.onNext(infos);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Object>>() {
            @Override
            public void accept(final List<Object> colorInfos) throws Exception {
                mProgressLoad.setVisibility(View.GONE);
                ForcaestInfoAdapter adapter = new ForcaestInfoAdapter(getContext(), R.layout.adapter_forcaest_info, colorInfos);
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
