package fjnu.edu.cn.xjsscttjh.fragment;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import org.xutils.view.annotation.ViewInject;

import fjnu.edu.cn.xjsscttjh.R;


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
    public void pageFinished(WebView view, String url) {
        //view.setVisibility(View.VISIBLE);
        /*String fun="javascript:function getClass(parent) { var aEle=parent.getElementsByTagName('div'); var aResult=[]; var i=0; for(i<0;i<aEle.length;i++) { aResult.push(aEle[i]); }; return aResult; } ";

        view.loadUrl(fun);

        String fun2="javascript:function hideOther() {var j=0; var allDiv=getClass(document); for(j=0;j<allDiv.length;j++){ allDiv[j].style.display='none'}}";

        view.loadUrl(fun2);*/

        /*
        String hideOther = "javascript:function hideOther(){document.getElementById('footer').style.display = 'none';}";
        view.loadUrl(hideOther);
        String hideContent = "javascript:hideOther();";
        view.loadUrl(hideContent);

        String hideHeader = "javascript:function hideHeader(){document.getElementById('widePageHead').style.display = 'none';}";
        view.loadUrl(hideHeader);
        String jsHideHeader = "javascript:hideHeader();";
        view.loadUrl(jsHideHeader);

        String hideOtherInfo = "javascript:function hideOtherInfo(){document.getElementById('container').getElementsByTagName('div')[0].style.display = 'none';}";
        view.loadUrl(hideOtherInfo);
        String jsHideOtherInfo = "javascript:hideOtherInfo()";
        view.loadUrl(jsHideOtherInfo);

        String hideSpan = "javascript:function hideSpan(){document.getElementById('container').getElementsByTagName('span')[1].style.display = 'none';}";
        view.loadUrl(hideSpan);
        String jsHideSpan = "javascript:hideSpan()";
        view.loadUrl(jsHideSpan);
        */
        mProgressLoad.setVisibility(View.GONE);
        view.setVisibility(View.VISIBLE);

    }

}
