package fjnu.edu.cn.xjsscttjh.fragment;

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

import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.base.AppBaseFragment;
import fjnu.edu.cn.xjsscttjh.data.ConstData;
import fjnu.edu.cn.xjsscttjh.utils.LottyDataGetUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import momo.cn.edu.fjnu.androidutils.utils.ToastUtils;

/**
 * Created by Administrator on 2017\9\3 0003.
 * 网页浏览页面
 */

@ContentView(R.layout.fragment_browser)
public class FcTrendChartBrowserFragment extends AppBaseFragment {

    @ViewInject(R.id.web_info)
    private WebView mWebInfo;
    @ViewInject(R.id.progress_load)
    ProgressBar mProgressLoad;
    private String mLoadUrl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }


    @Override
    public void init() {
        super.init();
        mWebInfo.setFocusable(true);
        mWebInfo.setFocusableInTouchMode(true);
        mWebInfo.requestFocus();
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
        webSettings.setUserAgentString("Mozilla/5.0");
        webSettings.setLoadWithOverviewMode(true);
        updateWebSettings(webSettings);
        //加载需要显示的网页
        //mWebInfo.loadUrl(mLoadUrl);
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
                mProgressLoad.setVisibility(View.VISIBLE);
                pageStarted(view, url, favicon);
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
               // mProgressLoad.setVisibility(View.GONE);
                mProgressLoad.setVisibility(View.GONE);
                pageFinished(view, url);
                super.onPageFinished(view, url);

            }
        });

        mLoadUrl = getActivity().getIntent().getStringExtra(ConstData.IntentKey.WEB_LOAD_URL);
        //boolean isInformationUrl = getActivity().getIntent().getBooleanExtra(ConstData.IntentKey.IS_INFORMATION_URL, false);
        //if(isInformationUrl)
        //    mLoadUrl += "?from=client";
        //mWebInfo.loadUrl(mLoadUrl);
        mProgressLoad.setVisibility(View.VISIBLE);
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext( LottyDataGetUtils.getTrendChardHtmlByFc(mLoadUrl));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                mProgressLoad.setVisibility(View.GONE);
                mWebInfo.loadDataWithBaseURL("http://www.es123.com/", s, "text/html;charset=UTF-8", null, null);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mProgressLoad.setVisibility(View.GONE);
                ToastUtils.showToast("发生错误，请重试");
            }
        });
        mWebInfo.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_BACK ) {
                    if(mWebInfo.canGoBack()){
                        mWebInfo.goBack();
                        return true;
                    }
                    getActivity().finish();
                    return true;
                }
                return false;
            }
        });
    }

    public void pageStarted(WebView view, String url, Bitmap favicon){

    }

    public void pageFinished(WebView view, String url){

    }

    public void updateWebSettings(WebSettings settings){

    }
}
