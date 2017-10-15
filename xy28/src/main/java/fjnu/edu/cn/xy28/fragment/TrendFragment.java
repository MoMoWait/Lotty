package fjnu.edu.cn.xy28.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import fjnu.edu.cn.xy28.R;
import fjnu.edu.cn.xy28.base.AppBaseFragment;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by gaofei on 2017/10/15.
 * 图表加载
 */

@ContentView(R.layout.fragment_browser)
public class TrendFragment extends AppBaseFragment {

    public static final String TAG = "TrendFragment";

    public interface TrendURL{
        @GET("/")
        Call<ResponseBody> getHtml();
    }

    @ViewInject(R.id.web_info)
    private WebView mWebInfo;

    @ViewInject(R.id.progress_load)
    private ProgressBar mProgressLoad;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }


    @Override
    public void init() {
//        mWebInfo.setFocusable(true);
//        mWebInfo.setFocusableInTouchMode(true);
//        mWebInfo.requestFocus();
        WebSettings webSettings = mWebInfo.getSettings();
        webSettings.setDomStorageEnabled(true);
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        //支持缩放
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUserAgentString("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        //加载需要显示的网页
        mWebInfo.loadUrl("https://www.dandan29.com/index/trends");
        //设置Web视图
        mWebInfo.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //ToastUtils.showToast("shouldOverrideUrlLoading1");
                mWebInfo.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pageStarted(view, url, favicon);
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pageFinished(view, url);
                super.onPageFinished(view, url);

            }
        });


//        mWebInfo.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_BACK ) {
//                    if(mWebInfo.canGoBack()){
//                        mWebInfo.goBack();
//                        return true;
//                    }
//                    return getActivity().onKeyDown(i, keyEvent);
//                }
//                return false;
//            }
//        });

    }

    public void pageStarted(WebView view, String url, Bitmap favicon){
        mWebInfo.setVisibility(View.GONE);
        mProgressLoad.setVisibility(View.VISIBLE);
    }

    public void pageFinished(WebView view, String url){
        String hideOne="javascript:function hideOne(){var divElements = document.getElementsByTagName('div');" +
                "for(var i = 0; i < divElements.length; i++){var divClassName = divElements[i].className; if(divClassName=='top clear' ||" +
                "divClassName=='nav clear' || divClassName=='title_common px14 containers' || divClassName=='foot text-center' ||" +
                "divClassName=='trend-tip'){divElements[i].style.display='none'}}}";
        view.loadUrl(hideOne);
        String jsHideOne = "javascript:hideOne();";
        view.loadUrl(jsHideOne);

        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> e) throws Exception {
                Thread.sleep(1500);
                e.onNext(new Object());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                mProgressLoad.setVisibility(View.GONE);
                mWebInfo.setVisibility(View.VISIBLE);
            }
        });


    }
}
