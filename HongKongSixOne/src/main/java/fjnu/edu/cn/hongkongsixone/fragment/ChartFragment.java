package fjnu.edu.cn.hongkongsixone.fragment;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;



/**
 * Created by gaofei on 2017/10/10.
 * 图表浏览页
 */


public class ChartFragment extends BrowserFragment{

    @Override
    public void pageStarted(WebView view, String url, Bitmap favicon) {
        view.setVisibility(View.GONE);
    }

    @Override
    public void pageFinished(WebView view, String url) {
        view.setVisibility(View.VISIBLE);
        String fun="javascript:function getClass(parent) { var aEle=parent.getElementsByTagName('div'); var aResult=[]; var i=0; for(i<0;i<aEle.length;i++) { if(aEle[i].id!='waitBox' && aEle[i].id!='chartLinediv') { aResult.push(aEle[i]); } }; return aResult; } ";

        view.loadUrl(fun);

        String fun2="javascript:function hideOther() {var j=0; var allDiv=getClass(document); for(j=0;j<allDiv.length;j++){ allDiv[j].style.display='none'}}";

        view.loadUrl(fun2);

        view.loadUrl("javascript:hideOther();");
    }

}
