package fjnu.edu.cn.lotty.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import fjnu.edu.cn.lotty.R;
import fjnu.edu.cn.lotty.base.AppBaseFragment;

/**
 * Created by gaofei on 2017/9/10.
 * 发现页面
 */

@ContentView(R.layout.fragment_discovery)
public class DiscoveryFragment extends AppBaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void init() {

    }
}
