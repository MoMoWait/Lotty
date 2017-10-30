package fjnu.edu.cn.bjk3js.fragment;

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

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import fjnu.edu.cn.bjk3js.data.ConstData;
import fjnu.edu.cn.bjk3js.R;
import momo.cn.edu.fjnu.androidutils.base.BaseFragment;

/**
 * Created by Administrator on 2017\9\3 0003.
 * 网页浏览页面
 */

@ContentView(R.layout.fragment_browser)
public class BrowserFragment extends BaseFragment {

    @ViewInject(R.id.web_info)
    private WebView mWebInfo;

    private String mLoadUrl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }


    @Override
    public void init() {
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
        //webSettings.setUseWideViewPort(true);
        //webSettings.setLoadWithOverviewMode(true);
        updateOtherWebSettings(webSettings);
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
                pageStarted(view, url, favicon);
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pageFinished(view, url);
                super.onPageFinished(view, url);

            }
        });

        mLoadUrl = getActivity().getIntent().getStringExtra(ConstData.IntentKey.WEB_LOAD_URL);
        boolean isInformationUrl = getActivity().getIntent().getBooleanExtra(ConstData.IntentKey.IS_INFORMATION_URL, false);
        if(isInformationUrl)
            mLoadUrl += "?from=client";
        mWebInfo.loadUrl(mLoadUrl);

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

    public void updateOtherWebSettings(WebSettings webSettings){

    }
}
