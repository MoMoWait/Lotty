package fjnu.edu.cn.xjsscttjh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.adapter.ColorAdapter;
import fjnu.edu.cn.xjsscttjh.base.AppBaseFragment;
import fjnu.edu.cn.xjsscttjh.bean.ColorInfo;
import fjnu.edu.cn.xjsscttjh.data.ConstData;
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
                        if(isInit)
                            mListLottery.setAdapter(colorAdapter);
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
            URL url = new URL(ConstData.LOTTERY_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            byte data[] = new byte[2048];
            InputStream stream = connection.getInputStream();
            StringBuilder builder = new StringBuilder();
            int readLength = stream.read(data);
            while(readLength > 0){
                builder.append( new String(data, 0 , readLength, Charset.forName("UTF-8")));
                readLength = stream.read(data);

            }
            stream.close();
            String headContent = builder.toString();
            JSONObject headerObject = new JSONObject(headContent);
            JSONArray headerArray =  headerObject.getJSONObject("result").getJSONArray("list");
            for(int i = 0; i < headerArray.length(); ++i){
                ColorInfo colorInfo = new ColorInfo();
                JSONObject itemObject = headerArray.getJSONObject(i);
                colorInfo.setOpenDate(itemObject.getString("opendate"));
                colorInfo.setIssueNo(itemObject.getString("issueno"));
                colorInfo.setNumber(itemObject.getString("number"));
                infos.add(colorInfo);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return  infos;
    }
}
