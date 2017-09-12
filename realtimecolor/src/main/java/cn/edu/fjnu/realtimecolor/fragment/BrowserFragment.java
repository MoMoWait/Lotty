package cn.edu.fjnu.realtimecolor.fragment;

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

import cn.edu.fjnu.realtimecolor.R;
import cn.edu.fjnu.realtimecolor.data.ConstData;
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
        initView();
        initData();
        initEvent();
    }

    public void initView() {
        mWebInfo.setFocusable(true);
        mWebInfo.setFocusableInTouchMode(true);
        mWebInfo.requestFocus();
        WebSettings webSettings = mWebInfo.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        //支持缩放
        webSettings.setSupportZoom(true);
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
        });
    }

    public void initData() {
        mLoadUrl = getActivity().getIntent().getStringExtra(ConstData.IntentKey.WEB_LOAD_URL);
        boolean isInformationUrl = getActivity().getIntent().getBooleanExtra(ConstData.IntentKey.IS_INFORMATION_URL, false);
        if(isInformationUrl)
            mLoadUrl += "?from=client";
        mWebInfo.loadUrl(mLoadUrl);
    }

    public void initEvent() {
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


}
