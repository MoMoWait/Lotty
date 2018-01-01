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
import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.activity.BrowserActivity;
import fjnu.edu.cn.xjsscttjh.adapter.MessageAdapter;
import fjnu.edu.cn.xjsscttjh.base.AppBaseFragment;
import fjnu.edu.cn.xjsscttjh.bean.Message;
import fjnu.edu.cn.xjsscttjh.data.ConstData;
import fjnu.edu.cn.xjsscttjh.utils.CommonUtils;
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
                return CommonUtils.getMessages();
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
