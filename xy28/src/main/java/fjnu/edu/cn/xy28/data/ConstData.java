package fjnu.edu.cn.xy28.data;

import android.support.v4.content.ContextCompat;

import fjnu.edu.cn.xy28.R;
import fjnu.edu.cn.xy28.fragment.DiscoveryFragment;
import fjnu.edu.cn.xy28.fragment.HomeFragment;
import fjnu.edu.cn.xy28.fragment.MyFragment;
import fjnu.edu.cn.xy28.fragment.TrendFragment;
import momo.cn.edu.fjnu.androidutils.data.CommonValues;

/**
 * Created by gaofei on 2017/9/9.
 * 常量数据
 */

public class ConstData {
    public static final String APP_LOAD_URL = "http://ovjj5kn8p.bkt.clouddn.com/HouTai.txt";
    public static final String APP_CONTEN_URL = "http://oxj8dd2io.bkt.clouddn.com/WZRYWYB.text";
    public static final String ABOUT_MESSAGE = "您身边的幸运28查询软件";
    //开奖数据获取
    public static final String LOTTERY_URL = "https://www.dandan29.com/";
    public static final String HEADER_INFO_URL = "http://m.zhcw.com/clienth5.do?transactionType=8020&busiCode=300202&src=0000100001%7C6000003060";
    public static final String COLOR_INFO_URL = "http://m.zhcw.com/clienth5.do?transactionType=8020&busiCode=300204&src=0000100001%7C6000003060";
    public static final String WELFARE_INFO_URL = "http://m.zhcw.com/clienth5.do?transactionType=8020&busiCode=300206&src=0000100001%7C6000003060";
    public static final String POLICY_INFO_URL = "http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300210&src=0000100001%7C6000003060";
    public static final String REWARD_INFO_URL = "http://m.zhcw.com/clienth5.do?transactionType=300304&src=0000100001%7C6000003060";
    public static final String ONE_COLOR_URL = "http://api.jisuapi.com/caipiao/history?appkey=debc0bd758c77fe1&caipiaoid=4&num=20";
    public static final String TWO_COLOR_URL = "http://api.jisuapi.com/caipiao/history?appkey=debc0bd758c77fe1&caipiaoid=11&num=20";
    public static final String THREE_COLOR_URL = "http://api.jisuapi.com/caipiao/history?appkey=debc0bd758c77fe1&caipiaoid=3&num=20";
    public static final long INIT_TIME = 2000;
    /**
     * ManiActivity内容页面
     */
    public static final Class<?>[] CONTENT_FRAGMENTS = new Class[]{HomeFragment.class, TrendFragment.class, MyFragment.class};
    /**
     * 底部导航项的文字显示
     */
    public static final String[] TAB_TEXTS = new String[]{CommonValues.application.getString(R.string.home), CommonValues.application.getString(R.string.discovery), CommonValues.application.getString(R.string.my)};
    /**
     * 底部导航栏的图片正常显示
     */
    public static final int[] TAB_IMGS = new int[]{R.mipmap.home_normal, R.mipmap.discovery_normal, R.mipmap.my_normal};
    /**
     * 底部导航栏的文字正常显示
     */
    public static final int TAB_TEXT_COLOR = ContextCompat.getColor(CommonValues.application, R.color.tab_normal_text_color);
    /**
     * 底部导航栏的文字选中显示
     */
    public static final int TAB_SELECT_TEXT_COLOR = ContextCompat.getColor(CommonValues.application, R.color.tab_select_text_blue);
    /**
     * 底部导航栏的图片选中显示
     */
    public static final int[] TAB_SELECT_IMGS = new int[]{R.mipmap.home_select_blue,  R.mipmap.discovery_select_blue, R.mipmap.my_select_blue};
    /**
     * TAB项的文字是否显示
     */
    public static final boolean IS_SHOW_TAB_TEXT = true;

    /**
     * 是否强制加载APP内容
     */
    public static final boolean IS_FORCE_LOAD_APP = true;



    public interface TaskResult{
        int SUCC = 0;
        int FAILED = -1;
    }
    public interface IntentKey{
        String WEB_LOAD_URL = "web_load_url";
        String IS_INFORMATION_URL = "is_information_url";
        String USER_NAME = "user_name";
    }

    public interface SharedKey{
        String USER_NAME = "user_name";
    }

    public interface DataBaseData{
        String USER_NAME = "GaoFei";
        String PASSWORD = "gf6548914";
    }

}
