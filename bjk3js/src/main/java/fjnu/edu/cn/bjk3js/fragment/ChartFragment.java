package fjnu.edu.cn.bjk3js.fragment;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import org.xutils.view.annotation.ViewInject;

import fjnu.edu.cn.bjk3js.R;
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
        //view.setVisibility(View.VISIBLE);
        String hideOne="javascript:function hideOne(){document.getElementsByTagName('div')[4].style.display = 'none';}";
        view.loadUrl(hideOne);
        String jsHideOne = "javascript:hideOne();";
        view.loadUrl(jsHideOne);

        String hideTwo="javascript:function hideTwo(){document.getElementById('divMain').getElementsByTagName('div')[1].style.display = 'none';" +
                "document.getElementById('divMain').getElementsByTagName('div')[2].style.display = 'none';" +
                "document.getElementById('divMain').getElementsByTagName('div')[3].style.display = 'none';" +
                "document.getElementById('divMain').getElementsByTagName('div')[6].style.display = 'none';}";
        view.loadUrl(hideTwo);
        String jsHideTwo = "javascript:hideTwo();";
        view.loadUrl(jsHideTwo);


        String hideThree="javascript:function hideThree(){document.getElementById('divMain').getElementsByTagName('table')[0].style.display = 'none';}";
        view.loadUrl(hideThree);
        String jsHideThree = "javascript:hideThree();";
        view.loadUrl(jsHideThree);


        String hideFour="javascript:function hideFour(){var allDivs = document.getElementsByTagName('div');" +
                "for(var i = 0; i < allDivs.length; i++){if(allDivs[i].className == 'flottery' || allDivs[i].className == 'footer'){allDivs[i].style.display='none';}}}";
        view.loadUrl(hideFour);
        String jsHideFour = "javascript:hideFour();";
        view.loadUrl(jsHideFour);


        String hideFive="javascript:function hideFive(){var allDivs = document.getElementsByTagName('div');" +
                "for(var i = 0; i < allDivs.length; i++){if(allDivs[i].className == 'canshu' || allDivs[i].className == 'canshu_1' || " +
                "allDivs[i].className == 'sp_panel' || allDivs[i].className == 'sp_close'){allDivs[i].style.display='none' || allDivs[i].className == '';}}}";
        view.loadUrl(hideFive);
        String jsHideFive = "javascript:hideFive();";
        view.loadUrl(jsHideFive);


        String hideSix="javascript:function hideSix(){document.getElementById('tabtrend').getElementsByTagName('tfoot')[0].style.display='none'}";
        view.loadUrl(hideSix);
        String jsHideSix = "javascript:hideSix();";
        view.loadUrl(jsHideSix);


        String hideSenven="javascript:function hideSenven(){document.getElementById('guidemin').style.display='none'}";
        view.loadUrl(hideSenven);
        String jsHideSenven = "javascript:hideSenven();";
        view.loadUrl(jsHideSenven);

        String hideEight="javascript:function hideEight(){var allDivs = document.getElementsByTagName('canvas');" +
                "for(var i = 0; i < allDivs.length; i++){allDivs[i].style.display='none';}}";
        view.loadUrl(hideEight);
        String jsHideEight = "javascript:hideEight();";
        view.loadUrl(jsHideEight);

        String hideNine="javascript:function hideNine(){var headerBottom = document.getElementById('header-bottom');" +
                "headerBottom.style.display='none';headerBottom.previousSibling.previousSibling.style.display='none';}";
        view.loadUrl(hideNine);
        String jsHideNine = "javascript:hideNine();";
        view.loadUrl(jsHideNine);


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

}
