package fjnu.edu.cn.xjsscttjh.fragment;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import org.xutils.view.annotation.ViewInject;

import fjnu.edu.cn.xjsscttjh.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by gaofei on 2017/10/10.
 * 图表浏览页
 */


public class ChartFragment extends BrowserFragment{

    @ViewInject(R.id.progress_load)
    private ProgressBar mProgressLoad;

    @Override
    public void pageStarted(WebView view, String url, Bitmap favicon) {
        view.setVisibility(View.GONE);
        mProgressLoad.setVisibility(View.VISIBLE);
    }

    @Override
    public void pageFinished(final WebView view, String url) {
        String fun="javascript:function getClass(parent) { var aEle=parent.getElementsByTagName('div'); var aResult=[]; var i=0; for(i<0;i<aEle.length;i++) { if(aEle[i].id!='waitBox' && aEle[i].id!='chartLinediv') { aResult.push(aEle[i]); } }; return aResult; } ";

        view.loadUrl(fun);

        String fun2="javascript:function hideOther() {var j=0; var allDiv=getClass(document); for(j=0;j<allDiv.length;j++){ allDiv[j].style.display='none'}}";

        view.loadUrl(fun2);

        view.loadUrl("javascript:hideOther();");

        //mProgressLoad.setVisibility(View.VISIBLE);
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
                view.setVisibility(View.VISIBLE);
            }
        });
    }


    @Override
    public void updateWebSettings(WebSettings settings) {
        settings.setUserAgentString("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
    }
}
