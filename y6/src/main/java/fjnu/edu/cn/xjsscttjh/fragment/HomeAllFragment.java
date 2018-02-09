package fjnu.edu.cn.xjsscttjh.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.activity.FcTrendsInfoActivity;
import fjnu.edu.cn.xjsscttjh.activity.ForcaestInfoDisplayActivity;
import fjnu.edu.cn.xjsscttjh.activity.ForecastInfoActivity;
import fjnu.edu.cn.xjsscttjh.activity.LottyNowOpenActivity;
import fjnu.edu.cn.xjsscttjh.activity.WyJoinMethodActivity;
import fjnu.edu.cn.xjsscttjh.adapter.FcTrendTypeAdapter;
import fjnu.edu.cn.xjsscttjh.base.AppBaseFragment;
import fjnu.edu.cn.xjsscttjh.bean.TrendInfo;
import fjnu.edu.cn.xjsscttjh.data.ConstData;

/**
 * Created by gaofei on 2018/2/6.
 * 包含所有的内容页面
 */
@ContentView(R.layout.fragment_home_all)
public class HomeAllFragment extends AppBaseFragment implements View.OnClickListener{
    @ViewInject(R.id.grid_trends_type)
    GridView mGridTrendsType;

    @ViewInject(R.id.progress_load)
    ProgressBar mProgressLoad;

    @ViewInject(R.id.text_open_lotty)
    private TextView mTextOpenLotty;

    @ViewInject(R.id.text_forecast)
    private TextView mTextForecast;

    @ViewInject(R.id.text_join_method)
    private TextView mTextJoinMethod;

    private TrendInfoLoadTask mLoadTask;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void init() {
        super.init();
        loadData();
        initEvent();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.text_open_lotty:
                intent.setClass(getContext(), LottyNowOpenActivity.class);
                break;
            case R.id.text_forecast:
                intent.setClass(getContext(), ForecastInfoActivity.class);
                break;
            case R.id.text_join_method:
                intent.setClass(getContext(), WyJoinMethodActivity.class);
                break;
        }
        startActivity(intent);
    }

    private void initEvent(){
        mTextOpenLotty.setOnClickListener(this);
        mTextForecast.setOnClickListener(this);
        mTextJoinMethod.setOnClickListener(this);
    }

    private void loadData(){
        mLoadTask = new TrendInfoLoadTask(this);
        mProgressLoad.setVisibility(View.VISIBLE);
        mLoadTask.execute();
    }

    private static class TrendInfoLoadTask extends AsyncTask<String, Integer, List<TrendInfo>> {

        private WeakReference<HomeAllFragment> mFragment;

        public TrendInfoLoadTask(HomeAllFragment framgnet){
            mFragment = new WeakReference<>(framgnet);
        }

        @Override
        protected List<TrendInfo> doInBackground(String... strings) {
            List<TrendInfo> trendInfos = new ArrayList<>();
            Set<String> titles = ConstData.FC_LOTTY_IMG_URLS.keySet();
            for(String title : titles){
                TrendInfo trendInfo = new TrendInfo();
                trendInfo.setName(title);
                trendInfo.setImgUrl(ConstData.FC_LOTTY_IMG_URLS.get(title));
                trendInfos.add(trendInfo);
            }
            return trendInfos;
        }

        @Override
        protected void onPostExecute(final List<TrendInfo> trendInfos) {
            final HomeAllFragment fragment = mFragment.get();
            if(fragment != null && fragment.getContext() != null && fragment.isCanUpadteUI()){
                fragment.mProgressLoad.setVisibility(View.GONE);
                FcTrendTypeAdapter fcTrendTypeAdapter = new FcTrendTypeAdapter(fragment.getContext(), R.layout.adapter_all_lottery, trendInfos);
                fragment.mGridTrendsType.setAdapter(fcTrendTypeAdapter);
                fragment.mGridTrendsType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(fragment.getContext(), FcTrendsInfoActivity.class);
                        intent.putExtra(ConstData.IntentKey.LOTTERY_NAME, trendInfos.get(position).getName());
                        fragment.startActivity(intent);
                    }
                });
            }
        }
    }
}
