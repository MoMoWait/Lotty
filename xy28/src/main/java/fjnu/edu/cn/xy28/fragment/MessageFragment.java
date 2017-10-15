package fjnu.edu.cn.xy28.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import fjnu.edu.cn.xy28.R;
import fjnu.edu.cn.xy28.base.AppBaseFragment;

/**
 * Created by gaofei on 2017/9/9.
 * 消息页面
 */
@ContentView(R.layout.fragment_message)
public class MessageFragment extends AppBaseFragment {
    private static final String TAG = "MessageFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void init() {
        Log.i(TAG, "init");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestoryView");
    }
}
