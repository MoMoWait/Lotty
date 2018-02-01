package fjnu.edu.cn.xjsscttjh.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.base.AppBaseFragment;

/**
 * Created by gaofei on 2018/2/1.
 * 彩票开奖
 */
@ContentView(R.layout.fragment_now_open)
public class NowOpenFragment extends AppBaseFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void init() {
        super.init();
    }
}
