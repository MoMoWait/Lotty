package fjnu.edu.cn.xjsscttjh.fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
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
 * Created by gaofei on 2018/2/4.
 * 预测页面显示
 */
@ContentView(R.layout.fragment_forcaest_display)
public class ForcaestInfoDisplayFragment extends AppBaseFragment{

    private String mTitle;
    private String mUrl;
    private String mTime;

    @ViewInject(R.id.progress_load)
    private ProgressBar mProgressLoad;

    @ViewInject(R.id.text_title)
    private TextView mTextTitle;

    @ViewInject(R.id.text_time)
    private TextView mTextTime;

    @ViewInject(R.id.web_info)
    private WebView mWebInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
        //webSettings.setLoadWithOverviewMode(true);
        //加载需要显示的网页
        //mWebInfo.loadUrl(mLoadUrl);
        //设置Web视图
        mWebInfo.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //ToastUtils.showToast("shouldOverrideUrlLoading1");
                //mWebInfo.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgressLoad.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressLoad.setVisibility(View.GONE);
                super.onPageFinished(view, url);

            }
        });
        loadData();
    }

    private void loadData(){
        mTitle = getActivity().getIntent().getStringExtra(ConstData.IntentKey.WEB_LOAD_TITLE);
        mUrl = getActivity().getIntent().getStringExtra(ConstData.IntentKey.WEB_LOAD_URL);
        mTime = getActivity().getIntent().getStringExtra(ConstData.IntentKey.WEB_LOAD_TIME);
        mProgressLoad.setVisibility(View.VISIBLE);
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext(LottyDataGetUtils.getForcaestInfoByWY(mUrl));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String result) throws Exception {
                mProgressLoad.setVisibility(View.GONE);
                mTextTitle.setText(Html.fromHtml("<font>" + mTitle + "</font>"));
                mTextTime.setText(Html.fromHtml("<font>" + mTime + "</font>"));
                mWebInfo.loadDataWithBaseURL("http://cai.163.com/", result, "text/html;charset=UTF-8", null, null);
                //mTextContent.setText(Html.fromHtml(result));

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mProgressLoad.setVisibility(View.GONE);
                ToastUtils.showToast("发生异常，请重试");
            }
        });
    }
}
