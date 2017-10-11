package cn.edu.fjnu.racingplan.fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.xutils.view.annotation.ContentView;
import org.xutils.x;
import java.util.concurrent.TimeUnit;
import cn.edu.fjnu.racingplan.R;
import cn.edu.fjnu.racingplan.activity.AppBaseActivity;
import cn.edu.fjnu.racingplan.activity.BrowserActivity;
import cn.edu.fjnu.racingplan.activity.MainActivity;
import cn.edu.fjnu.racingplan.data.ConstData;
import cn.edu.fjnu.racingplan.model.AppLoadTask;
import cn.edu.fjnu.racingplan.model.ContentLoadTask;
import momo.cn.edu.fjnu.androidutils.base.BaseFragment;
import momo.cn.edu.fjnu.androidutils.utils.NetWorkUtils;

/**
 * 初始化封面
 * Created by GaoFei on 2016/3/24.
 */
@ContentView(R.layout.fragment_init)
public class InitFragment extends AppBaseFragment{

    private InitTask mInitTask;
    private AppLoadTask mLoadTask;
    private ContentLoadTask mContentTask;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }


    @Override
    public void init() {
        initData();

    }


    public void initData() {
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
                   if(getActivity() != null)
                        getActivity().finish();
               }
            }

            @Override
            public void showNetworkError() {
                showNetWorkErrorDialog();
            }
        });
        mContentTask = new ContentLoadTask(new ContentLoadTask.Callback() {
            @Override
            public void onResult(int status, String url) {
                //ToastUtils.showToast("内容加载:" + status);
                if(url != null){
                    //跳转至指定的网页
                    if(getActivity() != null){
                        Intent intent = new Intent(getActivity(), BrowserActivity.class);
                        intent.putExtra(ConstData.IntentKey.WEB_LOAD_URL, url);
                        startActivity(intent);
                        getActivity().finish();
                    }

                }else{
                    //加载应用页面
                    if(getActivity() != null){
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().overridePendingTransition(R.anim.activity_enter_right, R.anim.activity_enter_left);
                        getActivity().finish();
                    }

                }
            }
        });
        if(NetWorkUtils.haveInternet(getContext()))
            mInitTask.execute();
        else
            showNetWorkErrorDialog();
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mInitTask.getStatus() == AsyncTask.Status.RUNNING)
            mInitTask.cancel(true);
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
                if(NetWorkUtils.haveInternet(getContext()))
                    mLoadTask.execute();
                else
                    showNetWorkErrorDialog();
              /*  startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().overridePendingTransition(R.anim.activity_enter_right, R.anim.activity_enter_left);
                getActivity().finish();*/
            }else{
                showNetWorkErrorDialog();
            }
        }
    }


}
