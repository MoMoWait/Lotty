package fjnu.edu.cn.xy28.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import fjnu.edu.cn.xy28.R;
import fjnu.edu.cn.xy28.adapter.ColorAdapter;
import fjnu.edu.cn.xy28.base.AppBaseFragment;
import fjnu.edu.cn.xy28.bean.ColorInfo;
import fjnu.edu.cn.xy28.data.ConstData;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gaofei on 2017/9/9.
 * 首页
 */
@ContentView(R.layout.fragment_home)
public class HomeFragment extends AppBaseFragment {
    @ViewInject(R.id.list_lottery)
    private ListView mListLottery;

    @ViewInject(R.id.progress_load)
    private ProgressBar mProgressLoad;

    public static final String TAG = "HomeFragment";
    private boolean isInit = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void init() {
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
        mListLottery.setVisibility(View.GONE);
        mProgressLoad.setVisibility(View.VISIBLE);
        Object emptyObj = new Object();
        Observable.just(emptyObj).map(new Function<Object, List<ColorInfo>>() {
            @Override
            public List<ColorInfo> apply(@NonNull Object o) throws Exception {
                return getHomeColorInfos();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ColorInfo>>() {
                    @Override
                    public void accept(List<ColorInfo> colorInfos) throws Exception {
                        //设置适配器
                        ColorAdapter colorAdapter = new ColorAdapter(getActivity(), R.layout.adapter_color, 0, colorInfos);
                        if(isInit){
                            mListLottery.setAdapter(colorAdapter);
                            mProgressLoad.setVisibility(View.GONE);
                            mListLottery.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }


    /**
     * 获取开奖列表信息,在IO线程中执行
     * @return
     */
    public List<ColorInfo> getHomeColorInfos(){
        List<ColorInfo> infos = new ArrayList<>();
        //请求HTTP数据
        try{
            Document doc = Jsoup.connect("https://www.dandan29.com/").get();
            Elements luckys = doc.getElementsByClass("list");
            Element luck =  luckys.get(0);
            Element bodyData = luck.getElementsByTag("tbody").get(0);
            Elements allRowData = bodyData.getElementsByTag("tr");
            for(int i = 1; i < allRowData.size(); ++i){
                Element itElement = allRowData.get(i);
                Elements tdElements = itElement.getElementsByTag("td");
                ColorInfo colorInfo = new ColorInfo();
                colorInfo.setIssueNo(tdElements.get(0).text());
                colorInfo.setOpenDate(tdElements.get(1).text());
                colorInfo.setNumber(tdElements.get(2).text().split("=")[1]);
                infos.add(colorInfo);
            }

        }catch (Exception e){

        }
        return  infos;
    }
}
