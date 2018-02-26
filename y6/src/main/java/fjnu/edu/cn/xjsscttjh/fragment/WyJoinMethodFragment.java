package fjnu.edu.cn.xjsscttjh.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.activity.WyJoinMethodDisplayActivity;
import fjnu.edu.cn.xjsscttjh.adapter.WyJoinMethodAdapter;
import fjnu.edu.cn.xjsscttjh.base.AppBaseFragment;
import fjnu.edu.cn.xjsscttjh.data.ConstData;

/**
 * Created by gaofei on 2018/2/8.
 * 网易彩票玩法页面
 */
@ContentView(R.layout.fragment_wy_join)
public class WyJoinMethodFragment extends AppBaseFragment{

    @ViewInject(R.id.grid_titles)
    private GridView mGridTitles;

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
        final Map<String, String> joinMethods = ConstData.WY_LOTTY_JOIN_METHOD_URLS;
        final List<String> allTitles = new ArrayList<>();
        allTitles.addAll(joinMethods.keySet());
        WyJoinMethodAdapter adapter = new WyJoinMethodAdapter(getContext(), R.layout.adapter_wy_join_method, R.id.text_lotty_title, allTitles);
        mGridTitles.setAdapter(adapter);
        mGridTitles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), WyJoinMethodDisplayActivity.class);
                intent.putExtra(ConstData.IntentKey.TARGET_ACTIVITY_LABEL, allTitles.get(position) + "玩法介绍");
                intent.putExtra(ConstData.IntentKey.WEB_LOAD_URL, joinMethods.get(allTitles.get(position)));
                startActivity(intent);
            }
        });
    }
}
