package fjnu.edu.cn.xjsscttjh.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.activity.BrowserActivity;
import fjnu.edu.cn.xjsscttjh.adapter.MessageAdapter;
import fjnu.edu.cn.xjsscttjh.base.AppBaseFragment;
import fjnu.edu.cn.xjsscttjh.bean.Message;
import fjnu.edu.cn.xjsscttjh.bean.VideoInfo;
import fjnu.edu.cn.xjsscttjh.data.ConstData;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gaofei on 2017/12/3.
 * 彩讯模块
 */

@ContentView(R.layout.fragment_color_info)
public class ColorInfoFragment extends AppBaseFragment implements AdapterView.OnItemClickListener{
    @ViewInject(R.id.list_color_info)
    private ListView mListColorInfo;
    @ViewInject(R.id.progress_load)
    private ProgressBar mProgressLoad;
    private boolean mIsInit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }
    @Override
    public void init() {
        super.init();
        mIsInit = true;
        loadVideoData();
    }


    @Override
    public void onDestroyView() {
        mIsInit = false;
        super.onDestroyView();
    }

    private void loadVideoData(){
        mListColorInfo.setVisibility(View.GONE);
        mProgressLoad.setVisibility(View.VISIBLE);
        Object emptyObj = new Object();
        Observable.just(emptyObj).map(new Function<Object, List<Message>>() {
            @Override
            public List<Message> apply(@NonNull Object o) throws Exception {
                return getMessages();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Message>>() {
                    @Override
                    public void accept(List<Message> videoInfos) throws Exception {
                        MessageAdapter adapter = new MessageAdapter(getContext(), R.layout.adapter_message, videoInfos);
                        if(mIsInit){
                            mProgressLoad.setVisibility(View.GONE);
                            mListColorInfo.setVisibility(View.VISIBLE);
                            mListColorInfo.setAdapter(adapter);
                            mListColorInfo.setOnItemClickListener(ColorInfoFragment.this);
                        }

                    }
                });
    }

    public List<Message> getMessages(){
        List<Message> infos = new ArrayList<>();
        //请求HTTP数据
        try{
            URL oneURL = new URL(ConstData.HEADER_INFO_URL);
            HttpURLConnection connection = (HttpURLConnection) oneURL.openConnection();
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
            JSONObject oneObject = new JSONObject(headContent);
            JSONArray oneArray =  oneObject.getJSONArray("dataList");
            for(int i = 0; i < oneArray.length(); ++i){
                Message message = new Message();
                JSONObject itemObject = oneArray.getJSONObject(i);
                message.setTitle(itemObject.getString("title"));
                message.setPicUrl(itemObject.getString("logoFile"));
                message.setContentUrl(itemObject.getString("url"));
                infos.add(message);
            }


            URL twoURL = new URL(ConstData.COLOR_INFO_URL);
            connection = (HttpURLConnection) twoURL.openConnection();
            data = new byte[2048];
            stream = connection.getInputStream();
            builder = new StringBuilder();
            readLength = stream.read(data);
            while(readLength > 0){
                builder.append( new String(data, 0 , readLength, Charset.forName("UTF-8")));
                readLength = stream.read(data);

            }
            stream.close();
            headContent = builder.toString();
            JSONObject twoObject = new JSONObject(headContent);
            JSONArray twoArray =  twoObject.getJSONArray("dataList");
            for(int i = 0; i < twoArray.length(); ++i){
                Message message = new Message();
                JSONObject itemObject = twoArray.getJSONObject(i);
                message.setTitle(itemObject.getString("title"));
                message.setPicUrl(itemObject.getString("logoFile"));
                message.setContentUrl(itemObject.getString("url"));
                infos.add(message);
            }


            URL threeURL = new URL(ConstData.WELFARE_INFO_URL);
            connection = (HttpURLConnection) threeURL.openConnection();
            data = new byte[2048];
            stream = connection.getInputStream();
            builder = new StringBuilder();
            readLength = stream.read(data);
            while(readLength > 0){
                builder.append( new String(data, 0 , readLength, Charset.forName("UTF-8")));
                readLength = stream.read(data);

            }
            stream.close();
            headContent = builder.toString();
            JSONObject threeObject = new JSONObject(headContent);
            JSONArray threeArray =  threeObject.getJSONArray("dataList");
            for(int i = 0; i < twoArray.length(); ++i){
                Message message = new Message();
                JSONObject itemObject = threeArray.getJSONObject(i);
                message.setTitle(itemObject.getString("title"));
                message.setPicUrl(itemObject.getString("logoFile"));
                message.setContentUrl(itemObject.getString("url"));
                infos.add(message);
            }



        }catch (Exception e){

        }
        return  infos;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Message itemMessage = (Message) adapterView.getAdapter().getItem(i);
        //跳转至指定的网页
        Intent intent = new Intent(getActivity(), BrowserActivity.class);
        intent.putExtra(ConstData.IntentKey.WEB_LOAD_URL, itemMessage.getContentUrl());
        intent.putExtra(ConstData.IntentKey.IS_INFORMATION_URL, true);
        startActivity(intent);
    }

}
