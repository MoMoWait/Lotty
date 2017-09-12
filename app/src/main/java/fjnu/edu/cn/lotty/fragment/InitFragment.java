package fjnu.edu.cn.lotty.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.concurrent.TimeUnit;

import fjnu.edu.cn.lotty.R;
import fjnu.edu.cn.lotty.activity.BrowserActivity;
import fjnu.edu.cn.lotty.activity.MainActivity;
import fjnu.edu.cn.lotty.data.ConstData;
import fjnu.edu.cn.lotty.task.AppLoadTask;
import fjnu.edu.cn.lotty.task.ContentLoadTask;
import momo.cn.edu.fjnu.androidutils.base.BaseFragment;

/**
 * 初始化封面
 * Created by GaoFei on 2016/3/24.
 */
@ContentView(R.layout.fragment_init)
public class InitFragment extends BaseFragment{

    private InitTask mInitTask;
    private AppLoadTask mLoadTask;
    private ContentLoadTask mContentTask;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mInitTask.getStatus() == AsyncTask.Status.RUNNING)
            mInitTask.cancel(true);
    }


    @Override
    public void init() {
        //设置状态栏颜色
        getActivity().getWindow().setStatusBarColor(Color.parseColor("#ff8916"));
        mInitTask = new InitTask();
        mLoadTask = new AppLoadTask(new AppLoadTask.Callback() {
            @Override
            public void onResult(int status) {
                //获取结果如下:
                if(status == 0){
                    //请求内容加载接口
                    mContentTask.execute();
                }else{
                    //直接退出
                    getActivity().finish();
                }
            }
        });
        mContentTask = new ContentLoadTask(new ContentLoadTask.Callback() {
            @Override
            public void onResult(int status, String url) {
                //ToastUtils.showToast("内容加载:" + status);
                if(url != null){
                    //跳转至指定的网页
                    Intent intent = new Intent(getActivity(), BrowserActivity.class);
                    intent.putExtra(ConstData.IntentKey.WEB_LOAD_URL, url);
                    startActivity(intent);
                    getActivity().finish();
                }else{
                    //加载应用页面
                    startActivity(new Intent(getActivity(), MainActivity.class));
                    getActivity().overridePendingTransition(R.anim.activity_enter_right, R.anim.activity_enter_left);
                    getActivity().finish();
                }
            }
        });
        mInitTask.execute();
    }

    public class InitTask extends AsyncTask<String, Integer, Integer>{
        @Override
        protected Integer doInBackground(String... params) {
            try {
                    TimeUnit.MILLISECONDS.sleep(ConstData.INIT_TIME);
                    return ConstData.TaskResult.SUCC;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return ConstData.TaskResult.FAILED;
            }
        }

        @Override
        protected void onPostExecute(Integer result) {
            if(result == ConstData.TaskResult.SUCC){
                //请求接口，判断是否进入应用主页
                mLoadTask.execute();
              /*  startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().overridePendingTransition(R.anim.activity_enter_right, R.anim.activity_enter_left);
                getActivity().finish();*/
            }
        }
    }


}
