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
import java.util.List;
import java.util.Map;
import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.adapter.HistoryOpenDisplayAdapter;
import fjnu.edu.cn.xjsscttjh.base.AppBaseFragment;
import fjnu.edu.cn.xjsscttjh.bean.ColorInfo;
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
 * Created by gaofei on 2018/2/3.
 * 历史开奖
 */
@ContentView(R.layout.fragment_history_open)
public class HistoryOpenFragment extends AppBaseFragment{

    @ViewInject(R.id.list_now_open)
    private ListView mListNowOpen;
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
        Observable.create(new ObservableOnSubscribe<List<ColorInfo>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ColorInfo>> e) throws Exception {
                String title = getActivity().getIntent().getStringExtra(ConstData.IntentKey.LOTTERY_NAME);
                Map<String, List<ColorInfo>>  colorInfoMap = LottyDataGetUtils.getHistoryOpenInfoByFc();
                e.onNext(colorInfoMap.get(title));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<ColorInfo>>() {
            @Override
            public void accept(final List<ColorInfo> colorInfos) throws Exception {
                mProgressLoad.setVisibility(View.GONE);
                HistoryOpenDisplayAdapter adapter = new HistoryOpenDisplayAdapter(getContext(), R.layout.adapter_lottery_display, 0, colorInfos);
                mListNowOpen.setAdapter(adapter);
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
