package fjnu.edu.cn.xjsscttjh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

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
import java.util.Locale;
import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.adapter.LotteryDisplayAdapter;
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
 * Created by gaofei on 2017/12/1.
 * 彩票显示页面
 */

@ContentView(R.layout.fragment_lottery_display)
public class LotteryDisplayFragment extends AppBaseFragment{
    @ViewInject(R.id.list_lottery_display)
    private ListView mListLotteryDisplay;
    @ViewInject(R.id.progress_load)
    private ProgressBar mProgressLoad;
    private boolean mIsInit;
    /**彩票ID*/
    private int mLotteryId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsInit = false;
    }

    @Override
    public void init() {
        super.init();
        mIsInit = true;
        loadData();
    }

    private void loadData(){
        mLotteryId = getActivity().getIntent().getIntExtra(ConstData.IntentKey.LOTTERY_ID, 1);
        asyncLoadColorInfo(mLotteryId);
    }





    /**
     * 获取开奖列表信息,在IO线程中执行
     * @return
     */
    public List<ColorInfo> getHomeColorInfos(int colorID){
        List<ColorInfo> infos = new ArrayList<>();
        //请求HTTP数据
        try{
            URL url = new URL(String.format(Locale.CHINA, ConstData.BASE_LOTTERY_URL, colorID));
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

    public void asyncLoadColorInfo(int colorId){
        mListLotteryDisplay.setVisibility(View.GONE);
        mProgressLoad.setVisibility(View.VISIBLE);
        Observable.just(colorId).map(new Function<Integer, List<ColorInfo>>() {
            @Override
            public List<ColorInfo> apply(@NonNull Integer o) throws Exception {
                return getHomeColorInfos(o);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ColorInfo>>() {
                    @Override
                    public void accept(List<ColorInfo> colorInfos) throws Exception {
                        //设置适配器
                        LotteryDisplayAdapter displayAdapter = new LotteryDisplayAdapter(getActivity(), R.layout.adapter_lottery_display, 0, colorInfos);
                        if(mIsInit){
                            mListLotteryDisplay.setAdapter(displayAdapter);
                            mProgressLoad.setVisibility(View.GONE);
                            mListLotteryDisplay.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }
}
