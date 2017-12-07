package fjnu.edu.cn.xjsscttjh.data;

import android.support.v4.content.ContextCompat;

import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.bean.ColorType;
import fjnu.edu.cn.xjsscttjh.bean.ToolInfo;
import fjnu.edu.cn.xjsscttjh.fragment.AreaCodeSearchFragment;
import fjnu.edu.cn.xjsscttjh.fragment.DiscoveryFragment;
import fjnu.edu.cn.xjsscttjh.fragment.ExchangeSearchFragment;
import fjnu.edu.cn.xjsscttjh.fragment.HomeFragment;
import fjnu.edu.cn.xjsscttjh.fragment.MessageFragment;
import fjnu.edu.cn.xjsscttjh.fragment.MyFragment;
import fjnu.edu.cn.xjsscttjh.fragment.PhoneCodeSearchFragment;
import fjnu.edu.cn.xjsscttjh.fragment.ToolkitFragment;
import fjnu.edu.cn.xjsscttjh.fragment.TrainSearchFragment;
import fjnu.edu.cn.xjsscttjh.fragment.WeatherSearchFragment;
import momo.cn.edu.fjnu.androidutils.data.CommonValues;
import momo.cn.edu.fjnu.androidutils.utils.DeviceInfoUtils;

/**
 * Created by gaofei on 2017/9/9.
 * 常量数据
 */

public class ConstData {
    public static final String APP_LOAD_URL = "http://ovjj5kn8p.bkt.clouddn.com/HouTai.txt";
    /**应用宝*/
    //public static final String APP_CONTEN_URL = "http://appmgr.jwoquxoc.com/frontApi/getAboutUs?appid=app699cp100";
    public static final String APP_CONTEN_URL = "http://185.216.248.94:8080/biz/getAppConfig?appid=9723";
    public static final String ABOUT_MESSAGE = "您身边的时时彩查询助手";
    //极速数据的APP_KEY
    public static final String JS_APP_KEY = "400d09f5f73fbc71";
    public static final String VIDEO_URL = "http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300209&src=0000100001%7C6000003060";
    //开奖数据获取
    public static final String LOTTERY_URL = "http://api.jisuapi.com/caipiao/history?appkey=400d09f5f73fbc71&caipiaoid=90&issueno=&start=0&num=20";
    //基本获取彩票开奖数据的URL
    public static final String BASE_LOTTERY_URL = "http://api.jisuapi.com/caipiao/history?appkey=400d09f5f73fbc71&caipiaoid=%d&issueno=&start=0&num=20";
    //号码归属地查询URL
    public static final String BASE_PHONE_CODE_URL = "http://api.jisuapi.com/shouji/";
    //天气查询URL
    public static final String BASE_WEATHER_URL = "http://api.jisuapi.com/weather/";
    //区号查询URL
    public static final String BASE_AREA_CODE_URL = "http://api.jisuapi.com/areacode/";
    //汇率查询
    public static final String BASE_EXCHANGE_URL = "http://api.jisuapi.com/exchange/";
    //火车查询
    public static final String BASE_TRAIN_URL = "http://api.jisuapi.com/train/";
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
     * 屏幕宽
     */
    public static final int SCREEN_WIDTH = DeviceInfoUtils.getScreenWidth(CommonValues.application);
    /**
     * ManiActivity内容页面
     */
    public static final Class<?>[] CONTENT_FRAGMENTS = new Class[]{HomeFragment.class, ToolkitFragment.class, MyFragment.class};
    /**
     * 底部导航项的文字显示
     */
    public static final String[] TAB_TEXTS = new String[]{CommonValues.application.getString(R.string.home), CommonValues.application.getString(R.string.toolkit), CommonValues.application.getString(R.string.my)};
    /**
     * 标题文字，可自定义
     */
    public static final String[] TITLE_TEXTS = TAB_TEXTS;
    /**
     * 彩票类型
     */
    public static ColorType[] COLOR_TYPES = new ColorType[]{ new ColorType(90, "新疆时时彩"),new ColorType(73, "重庆时时彩"), new ColorType(93, "天津时时彩"), new ColorType(134, "云南时时彩")};
    /**
     * 底部导航栏的图片正常显示
     */
    public static final int[] TAB_IMGS = new int[]{R.mipmap.home_normal, R.mipmap.category_normal, R.mipmap.my_normal};
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
    public static final int[] TAB_SELECT_IMGS = new int[]{R.mipmap.home_select_blue,  R.mipmap.category_select_blue, R.mipmap.my_select_blue};
    /**
     * TAB项的文字是否显示
     */
    public static final boolean IS_SHOW_TAB_TEXT = true;

    /**
     * 是否强制加载APP内容
     */
    public static final boolean IS_FORCE_LOAD_APP = false;

    /**
     * 标题是否居中显示
     */
    public static final boolean IS_TITLE_CENTER_DISPLAY = true;

    /**
     * 工具类别
     */
    public static final ToolInfo[] TOOL_INFOS = new ToolInfo[]{
            new ToolInfo(R.mipmap.areacode, CommonValues.application.getString(R.string.area_code_search), AreaCodeSearchFragment.class.getName(), CommonValues.application.getString(R.string.area_code_search)),
            new ToolInfo(R.mipmap.exchange, CommonValues.application.getString(R.string.exchange_search), ExchangeSearchFragment.class.getName(), CommonValues.application.getString(R.string.exchange_search)),
            new ToolInfo(R.mipmap.shouji, CommonValues.application.getString(R.string.phone_area), PhoneCodeSearchFragment.class.getName(), CommonValues.application.getString(R.string.phone_area)),
            new ToolInfo(R.mipmap.train, CommonValues.application.getString(R.string.train_search), TrainSearchFragment.class.getName(), CommonValues.application.getString(R.string.train_search)),
            new ToolInfo(R.mipmap.weather, CommonValues.application.getString(R.string.weather_search), WeatherSearchFragment.class.getName(), CommonValues.application.getString(R.string.weather_search))};
    public interface TaskResult{
        int SUCC = 0;
        int FAILED = -1;
    }
    public interface IntentKey{
        String WEB_LOAD_URL = "web_load_url";
        String IS_INFORMATION_URL = "is_information_url";
        String USER_NAME = "user_name";
        String TARGET_FRAGMENT = "target_fragment";
        String TARGET_ACTIVITY_LABEL = "target_activity_label";
    }

    public interface SharedKey{
        String USER_NAME = "user_name";
    }

    public interface DataBaseData{
        String USER_NAME = "GaoFei";
        String PASSWORD = "gf6548914";
    }

}
