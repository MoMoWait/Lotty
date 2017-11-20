package fjnu.edu.cn.xjsscttjh.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import fjnu.edu.cn.xjsscttjh.activity.BrowserActivity;
import fjnu.edu.cn.xjsscttjh.base.AppBaseFragment;
import fjnu.edu.cn.xjsscttjh.bean.VideoInfo;
import fjnu.edu.cn.xjsscttjh.data.ConstData;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gaofei on 2017/9/9.
 * 消息页面
 */
@ContentView(R.layout.fragment_message)
public class MessageFragment extends AppBaseFragment implements AdapterView.OnItemClickListener{
    private boolean isInit = false;

    @ViewInject(R.id.list_videos)
    private ListView mListVideos;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void init() {
        isInit = true;
        loadVideoData();
    }


    @Override
    public void onDestroyView() {
        isInit = false;
        super.onDestroyView();
    }

    private void loadVideoData(){
        Object emptyObj = new Object();
        Observable.just(emptyObj).map(new Function<Object, List<VideoInfo>>() {
            @Override
            public List<VideoInfo> apply(@NonNull Object o) throws Exception {
                return getVideoInfos();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<VideoInfo>>() {
                    @Override
                    public void accept(List<VideoInfo> videoInfos) throws Exception {
                        ArrayAdapter<VideoInfo> adapter = new ArrayAdapter<VideoInfo>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, videoInfos);
                        if(isInit){
                            mListVideos.setAdapter(adapter);
                            mListVideos.setOnItemClickListener(MessageFragment.this);
                        }

                    }
                });
    }

    public List<VideoInfo> getVideoInfos(){
        List<VideoInfo> infos = new ArrayList<>();
        //请求HTTP数据
        try{
            URL url = new URL(ConstData.VIDEO_URL);
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
            JSONArray headerArray =  headerObject.getJSONArray("dataList");
            for(int i = 0; i < headerArray.length(); ++i){
                VideoInfo videoInfo = new VideoInfo();
                JSONObject itemObject = headerArray.getJSONObject(i);
                videoInfo.setTitle(itemObject.getString("title"));
                videoInfo.setVidoeUrl(itemObject.getString("url"));
                infos.add(videoInfo);
            }

        }catch (Exception e){

        }
        return  infos;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        VideoInfo itemVideoInfo = (VideoInfo) adapterView.getAdapter().getItem(i);
        //跳转至指定的网页
        Intent intent = new Intent(getActivity(), BrowserActivity.class);
        intent.putExtra(ConstData.IntentKey.WEB_LOAD_URL, itemVideoInfo.getVidoeUrl());
        intent.putExtra(ConstData.IntentKey.IS_INFORMATION_URL, true);
        startActivity(intent);
    }
}
