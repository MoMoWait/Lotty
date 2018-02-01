package fjnu.edu.cn.xjsscttjh.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.ref.WeakReference;
import java.util.List;

import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.activity.FcTrendsInfoActivity;
import fjnu.edu.cn.xjsscttjh.adapter.AllLotteryAdapter;
import fjnu.edu.cn.xjsscttjh.adapter.FcTrendTypeAdapter;
import fjnu.edu.cn.xjsscttjh.base.AppBaseFragment;
import fjnu.edu.cn.xjsscttjh.bean.TrendInfo;
import fjnu.edu.cn.xjsscttjh.data.ConstData;
import fjnu.edu.cn.xjsscttjh.utils.LottyDataGetUtils;

/**
 * Created by gaofei on 2018/1/30.
 * 发彩网走势类型
 */
@ContentView(R.layout.fragment_fc_trends_type)
public class FCTrendsTypeFramgnet extends AppBaseFragment{
    @ViewInject(R.id.grid_trends_type)
    GridView mGridTrendsType;

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
    }

    private void loadData(){
        mLoadTask = new TrendInfoLoadTask(this);
        mLoadTask.execute();
    }

    static class TrendInfoLoadTask extends AsyncTask<String, Integer, List<TrendInfo>>{

        private WeakReference<FCTrendsTypeFramgnet> mFragment;

        public TrendInfoLoadTask(FCTrendsTypeFramgnet framgnet){
            mFragment = new WeakReference<FCTrendsTypeFramgnet>(framgnet);
        }

        @Override
        protected List<TrendInfo> doInBackground(String... strings) {
            return LottyDataGetUtils.getAllTrendInfoByFC();
        }

        @Override
        protected void onPostExecute(final List<TrendInfo> trendInfos) {
           final FCTrendsTypeFramgnet fragment = mFragment.get();
           if(fragment != null && fragment.getContext() != null && fragment.isCanUpadteUI()){
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
