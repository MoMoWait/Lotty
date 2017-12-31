package fjnu.edu.cn.xjsscttjh.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

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
import fjnu.edu.cn.xjsscttjh.activity.ToolActivity;
import fjnu.edu.cn.xjsscttjh.adapter.ColorAdapter;
import fjnu.edu.cn.xjsscttjh.adapter.ColorTypeAdapter;
import fjnu.edu.cn.xjsscttjh.adapter.ToolAdapter;
import fjnu.edu.cn.xjsscttjh.base.AppBaseFragment;
import fjnu.edu.cn.xjsscttjh.bean.ColorInfo;
import fjnu.edu.cn.xjsscttjh.bean.ColorType;
import fjnu.edu.cn.xjsscttjh.bean.ToolInfo;
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
@ContentView(R.layout.fragment_toolkit)
public class ToolkitFragment extends AppBaseFragment {
    @ViewInject(R.id.grid_tools)
    private GridView mGridTools;

    public static final String TAG = "ToolkitFragment";
    private boolean isInit = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void init() {
        super.init();
        isInit = true;
        setRetainInstance(true);
        loadData();
        initEvent();
        Log.i(TAG, "init");
    }

    @Override
    public void onDestroyView() {
        isInit = false;
        super.onDestroyView();
        Log.i(TAG, "onDestoryView");
    }


    private void loadData(){
        ToolAdapter adapter = new ToolAdapter(getContext(), R.layout.adapter_tool, ConstData.TOOL_INFOS);
        mGridTools.setAdapter(adapter);
    }

    private void initEvent(){
        mGridTools.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToolInfo info = (ToolInfo) parent.getAdapter().getItem(position);
                Intent intent = new Intent(getContext(), ToolActivity.class);
                intent.putExtra(ConstData.IntentKey.TARGET_FRAGMENT, info.getTargetClassName());
                intent.putExtra(ConstData.IntentKey.TARGET_ACTIVITY_LABEL, info.getTargetActivityLabel());
                startActivity(intent);
            }
        });
    }
}
