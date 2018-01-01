package fjnu.edu.cn.xjsscttjh.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.activity.LotteryDisplayActivity;
import fjnu.edu.cn.xjsscttjh.adapter.AllLotteryAdapter;
import fjnu.edu.cn.xjsscttjh.adapter.ColorAdapter;
import fjnu.edu.cn.xjsscttjh.adapter.ColorTypeAdapter;
import fjnu.edu.cn.xjsscttjh.adapter.ImageAdapter;
import fjnu.edu.cn.xjsscttjh.base.AppBaseFragment;
import fjnu.edu.cn.xjsscttjh.bean.ColorInfo;
import fjnu.edu.cn.xjsscttjh.bean.ColorType;
import fjnu.edu.cn.xjsscttjh.bean.LotteryInfo;
import fjnu.edu.cn.xjsscttjh.bean.Message;
import fjnu.edu.cn.xjsscttjh.data.ConstData;
import fjnu.edu.cn.xjsscttjh.utils.CommonUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import momo.cn.edu.fjnu.androidutils.utils.DeviceInfoUtils;
import momo.cn.edu.fjnu.androidutils.utils.ToastUtils;

/**
 * Created by gaofei on 2017/9/9.
 * 彩票开奖页
 */
@ContentView(R.layout.fragment_all_lottery)
public class AllLotteryFragment extends AppBaseFragment {
    @ViewInject(R.id.list_lottery)
    private ListView mListLottery;
    @ViewInject(R.id.progress_load)
    private ProgressBar mProgressLoad;
    @ViewInject(R.id.pager_image)
    private ViewPager mPagerImage;

    public static final String TAG = "AllLotteryFragment";
    private static boolean isInit = false;
    private ImgHandler mHandler;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void init() {
        super.init();
        if(mHandler == null)
            mHandler = new ImgHandler(this);
        ViewGroup.LayoutParams pagerParams = mPagerImage.getLayoutParams();
        pagerParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        pagerParams.height = DeviceInfoUtils.getScreenWidth(getContext()) / 3;
        mPagerImage.setLayoutParams(pagerParams);
        isInit = true;
        setRetainInstance(true);
        loadLotteryData();
        Log.i(TAG, "init");
    }

    @Override
    public void onDestroyView() {
        isInit = false;
        super.onDestroyView();
        Log.i(TAG, "onDestoryView");
    }

    //加载APP数据
    private void loadLotteryData(){
        //设置适配器
        AllLotteryAdapter allLotteryAdapter = new AllLotteryAdapter(getContext(), R.layout.adapter_all_lottery, ConstData.ALL_LOTTERY_INFOS);
        mListLottery.setAdapter(allLotteryAdapter);

        mListLottery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LotteryInfo lotteryInfo = (LotteryInfo) adapterView.getAdapter().getItem(i);
                Intent intent = new Intent(getContext(), LotteryDisplayActivity.class);
                intent.putExtra(ConstData.IntentKey.LOTTERY_ID, lotteryInfo.getColorId());
                intent.putExtra(ConstData.IntentKey.LOTTERY_NAME, lotteryInfo.getDes());
                intent.putExtra(ConstData.IntentKey.TARGET_ACTIVITY_LABEL, lotteryInfo.getDes());
                startActivity(intent);
            }
        });

        asyncLoadImgs();
    }


    /**
     * 加载轮播图
     */
    public void asyncLoadImgs(){
       mProgressLoad.setVisibility(View.VISIBLE);
       mPagerImage.setVisibility(View.GONE);
       Observable.just(new Object()).map(new Function<Object, List<String> >() {
           @Override
           public List<String> apply(Object o) throws Exception {
               List<String> imgUrls = new ArrayList<>();
               for(Message itemMessage : CommonUtils.getMessages())
                   imgUrls.add(itemMessage.getPicUrl());
               return imgUrls;
           }
       }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<String>>() {
           @Override
           public void accept(List<String> imgUrls) throws Exception {
               mProgressLoad.setVisibility(View.GONE);
               mPagerImage.setVisibility(View.VISIBLE);
               ImageAdapter adapter = new ImageAdapter(getContext(), imgUrls);
               mPagerImage.setAdapter(adapter);
               mPagerImage.setCurrentItem(0);
               mHandler.sendEmptyMessageDelayed(100, 2000);

           }
       }, new Consumer<Throwable>() {
           @Override
           public void accept(Throwable throwable) throws Exception {
               ToastUtils.showToast("网络发生错误，请重试");
           }
       });
    }


    private void  changeImgPosition(){
        int position = mPagerImage.getCurrentItem();
        PagerAdapter pagerAdapter = mPagerImage.getAdapter();
        if(pagerAdapter != null){
            mPagerImage.setCurrentItem((position + 1) % pagerAdapter.getCount());
            mHandler.sendEmptyMessageDelayed(100, 2000);
        }


    }

    static class ImgHandler extends  Handler{
        private final WeakReference<AllLotteryFragment> mFragmentPreference;

        public ImgHandler(AllLotteryFragment fragment){
            mFragmentPreference = new WeakReference<AllLotteryFragment>(fragment);
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            if(msg.what == 100 && mFragmentPreference.get() != null && isInit){
               mFragmentPreference.get().changeImgPosition();
            }
        }
    }
}
