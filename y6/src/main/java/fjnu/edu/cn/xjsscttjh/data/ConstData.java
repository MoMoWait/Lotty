package fjnu.edu.cn.xjsscttjh.data;

import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.bean.ColorInfo;
import fjnu.edu.cn.xjsscttjh.bean.ColorType;
import fjnu.edu.cn.xjsscttjh.bean.LotteryInfo;
import fjnu.edu.cn.xjsscttjh.bean.TabItem;
import fjnu.edu.cn.xjsscttjh.bean.ToolInfo;
import fjnu.edu.cn.xjsscttjh.fragment.AllLotteryFragment;
import fjnu.edu.cn.xjsscttjh.fragment.AreaCodeSearchFragment;
import fjnu.edu.cn.xjsscttjh.fragment.ColorInfoFragment;
import fjnu.edu.cn.xjsscttjh.fragment.DiscoveryFragment;
import fjnu.edu.cn.xjsscttjh.fragment.ExchangeSearchFragment;
import fjnu.edu.cn.xjsscttjh.fragment.FCTrendsTypeFramgnet;
import fjnu.edu.cn.xjsscttjh.fragment.ForcaestInfoFragment;
import fjnu.edu.cn.xjsscttjh.fragment.HomeAllFragment;
import fjnu.edu.cn.xjsscttjh.fragment.HomeFragment;
import fjnu.edu.cn.xjsscttjh.fragment.MessageFragment;
import fjnu.edu.cn.xjsscttjh.fragment.MyFragment;
import fjnu.edu.cn.xjsscttjh.fragment.NowOpenFragment;
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
    /**彩票APK下载地址*/
    public static final String LOTTY_APK_URL = "https://apk.kosungames.com/app-diyicaipiao-release.apk";
    /**彩票APK基地址，用于Retrofit*/
    public static final String BASE_LOTTY_APK_URL = "https://apk.kosungames.com/";
    /**测试APK地址*/
    public static final String BASE_LOTTY_TEST_URL = "http://shouji.360tpcdn.com/180122/b7cf4b1ab3e338de4f13a2c258b9e483/";
    /**彩票APK的包名*/
    public static final String LOTTY_PKGNAME = "com.cp.diyicaipiao";
    /**彩票APK下的主页面*/
    public static final String LOTTY_LAUNCH_CLASS_NAME = "com.hatsune.MainActivity";
    /**彩票APK本地保存地址*/
    public static final String LOCAL_LOTTY_APK_PATH = Environment.getExternalStorageDirectory() + "/" + "lotty.apk";
    /**彩票更新时的背景图*/
    public static final String LOTTY_UPLOAD_BACKGROUND = "http://dycpcc.cpapp.diyiccapp.com/appqgtp/888.png";
    /**APK更新检查时间点*/
    public static final long APK_CHECK_UPDATE_TIME = getCheckUpdateTime();
    /**应用宝*/
    public static final String APP_CONTEN_URL = "http://www.27305.com/frontApi/getAboutUs?appid=11221726";
    public static final String ABOUT_MESSAGE = "您身边的时时彩查询助手";
    /**
     * 极速数据的APPKey
     */
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
    /**
     * 启动页面停留时间（ms）
     */
    public static final long INIT_TIME = 1500;
    /**
     * 屏幕宽
     */
    public static final int SCREEN_WIDTH = DeviceInfoUtils.getScreenWidth(CommonValues.application);
    /**
     * 底部导航栏的文字正常显示
     */
    public static final int TAB_TEXT_COLOR = ContextCompat.getColor(CommonValues.application, R.color.tab_normal_text_color);
    /**
     * 底部导航栏的文字选中显示
     */
    public static final int TAB_SELECT_TEXT_COLOR = ContextCompat.getColor(CommonValues.application, R.color.tab_select_text_green);
    /**
     * 底部导航项
     */
    public static final TabItem[] TAB_ITEMS = new TabItem[]{
            new TabItem(CommonValues.application.getString(R.string.app_name), R.mipmap.home_normal, R.mipmap.home_select_green, TAB_TEXT_COLOR, TAB_SELECT_TEXT_COLOR, HomeAllFragment.class),
            //new TabItem(CommonValues.application.getString(R.string.home), R.mipmap.home_normal, R.mipmap.home_select_green, TAB_TEXT_COLOR, TAB_SELECT_TEXT_COLOR, NowOpenFragment.class),
            //new TabItem(CommonValues.application.getString(R.string.discovery), R.mipmap.discovery_normal, R.mipmap.discovery_select_green, TAB_TEXT_COLOR, TAB_SELECT_TEXT_COLOR, FCTrendsTypeFramgnet.class),
            //new TabItem(CommonValues.application.getString(R.string.forcaest), R.mipmap.message_normal, R.mipmap.message_select_green, TAB_TEXT_COLOR, TAB_SELECT_TEXT_COLOR, ForcaestInfoFragment.class),
            //new TabItem(CommonValues.application.getString(R.string.my), R.mipmap.my_normal, R.mipmap.my_select_green, TAB_TEXT_COLOR, TAB_SELECT_TEXT_COLOR, MyFragment.class)
    };
    /**
     * ManiActivity内容页面
     */
    public static final List<Class<? extends Fragment>> CONTENT_FRAGMENTS = getContentFragmentClasss();
    /**
     * 彩票类型
     */
    public static ColorType[] COLOR_TYPES = new ColorType[]{ new ColorType(90, "新疆时时彩"),new ColorType(73, "重庆时时彩"), new ColorType(93, "天津时时彩"), new ColorType(134, "云南时时彩")};
    /**
     * 所有彩票种类
     */
    public static LotteryInfo[] ALL_LOTTERY_INFOS = new LotteryInfo[]{
            new LotteryInfo(R.mipmap.logo_3d, 12, "福彩3D"),
            new LotteryInfo(R.mipmap.logo_ahk3, 76, "安徽快3"),
            new LotteryInfo(R.mipmap.logo_dlt, 14, "大乐特"),
            new LotteryInfo(R.mipmap.logo_hljd11, 76, "山东11选5"),
            new LotteryInfo(R.mipmap.logo_jczq, 73, "竞彩足球"),
            new LotteryInfo(R.mipmap.logo_jclq, 93, "竞彩蓝球"),
            new LotteryInfo(R.mipmap.logo_qlc, 13, "七乐彩"),
            new LotteryInfo(R.mipmap.logo_ssq, 11, "双色球"),
    };
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
    /**
     * 发彩网的彩票图标URL
     */
    public static final Map<String, String> FC_LOTTY_IMG_URLS = getFcLottyImgUrls();
    /**
     * 发彩网开奖历史URL
     */
    public static final Map<String, String> FC_LOTTY_HISTORY_URLS = getFcLottyHistoryUrls();

    /**
     * 网易彩票预测URL
     */
    public static final Map<String, String> WY_LOTTY_FORECAST_URLS =  getWyLottyForecastUrls();

    /**
     * 网易彩票玩法URL
     */
    public static final Map<String, String> WY_LOTTY_JOIN_METHOD_URLS =  getWyLottyJoinMethodUrls();


    /**
     * 异步块执行结果
     */
    public interface TaskResult{
        int SUCC = 0;
        int FAILED = -1;
    }

    /**
     * 组件之间传递的key
     */
    public interface IntentKey{
        String WEB_LOAD_URL = "web_load_url";
        String IS_INFORMATION_URL = "is_information_url";
        String USER_NAME = "user_name";
        String TARGET_FRAGMENT = "target_fragment";
        String TARGET_ACTIVITY_LABEL = "target_activity_label";
        String LOTTERY_ID = "lottery_id";
        String LOTTERY_NAME = "lottery_name";
        String LOTTY_HISTORY_OPEN_URL = "lotty_history_open_url";
        String LOTTY_TREND_INFOS= "lotty_trend_infos";
        String WEB_LOAD_CONTENT = "web_load_content";
        String WEB_LOAD_TIME = "web_load_time";
        String WEB_LOAD_TITLE = "web_load_title";
    }

    /**
     * SharedPreference共享Key
     */
    public interface SharedKey{
        String USER_NAME = "user_name";
        /**马甲包安装时间*/
        String INSTALL_TIME = "install_time";
        /**下载的彩票包的MD5值*/
        String LOTTY_APK_MD5 = "lotty_apk_md5";
    }

    /**
     * 远程数据库配置
     */
    public interface DataBaseData{
        String USER_NAME = "GaoFei";
        String PASSWORD = "gf6548914";
    }

    /**
     * 获取检查更新时间点
     * @return
     */
    private static long getCheckUpdateTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 2, 13, 20, 0, 0);
        return calendar.getTime().getTime();
    }

    /**
     * 获取内容Fragment类
     * @return
     */
    private static List<Class<? extends Fragment>> getContentFragmentClasss(){
        List<Class<? extends Fragment>> tabFragmentClasss = new ArrayList<>();
        for(TabItem tabItem : ConstData.TAB_ITEMS){
            tabFragmentClasss.add(tabItem.getFragmentClass());
        }
        return tabFragmentClasss;
    }

    /**
     * 发彩网对应的彩票图标URL
     * @return
     */
    private static Map<String, String> getFcLottyImgUrls(){
        Map<String, String> imgUrlMap = new LinkedHashMap<>();
        imgUrlMap.put("双色球", "http://www.es123.com/images/lett_ssq.png");
        imgUrlMap.put("福彩3D", "http://www.es123.com/images/lett_fc3d.png");
        //imgUrlMap.put("重庆时时彩", "http://www.es123.com/images/lett_cqssc.png");
        imgUrlMap.put("七乐彩", "http://www.es123.com/images/lett_qlc.png");
        imgUrlMap.put("15选5", "http://www.es123.com/images/lett_l15x5.png");
        imgUrlMap.put("大乐透", "http://www.es123.com/images/lett_dlt.png");
        imgUrlMap.put("七星彩", "http://www.es123.com/images/lett_qxc.png");
        imgUrlMap.put("排列3", "http://www.es123.com/images/lett_pl3.png");
        imgUrlMap.put("排列5", "http://www.es123.com/images/lett_pl5.png");
        return imgUrlMap;
    }

    /**
     * 发彩网对应的彩票历史开奖地址
     * @return
     */
    private static Map<String, String> getFcLottyHistoryUrls(){
        Map<String, String> historyMap = new LinkedHashMap<>();
        historyMap.put("双色球", "http://www.es123.com/history/ssq/");
        historyMap.put("福彩3D", "http://www.es123.com/history/fc3d/");
        //historyMap.put("重庆时时彩", "http://www.es123.com/ssc/");
        historyMap.put("七乐彩", "http://www.es123.com/history/qlc/");
        historyMap.put("15选5", "http://www.es123.com/history/l15x5/");
        historyMap.put("大乐透", "http://www.es123.com/history/dlt/");
        historyMap.put("七星彩", "http://www.es123.com/history/qxc/");
        historyMap.put("排列3", "http://www.es123.com/history/pl3/");
        historyMap.put("排列5", "http://www.es123.com/history/pl5/");
        return historyMap;
    }

    /**
     * 彩票网易彩票预测结果
     * @return
     */
    private static Map<String, String> getWyLottyForecastUrls(){
        Map<String, String> forecastUrls = new LinkedHashMap<>();
        forecastUrls.put("双色球",  "http://cai.163.com/ssq/");
        forecastUrls.put("大乐透",  "http://cai.163.com/dlt/");
        forecastUrls.put("福彩3D",  "http://cai.163.com/3d/");
        forecastUrls.put("其他",  "http://cai.163.com/pl3/");
        return forecastUrls;
    }

    /**
     * 网易彩票玩法介绍URL
     * @return
     */
    private static Map<String, String> getWyLottyJoinMethodUrls(){
        Map<String, String> joinMethodUrls = new LinkedHashMap<>();
        joinMethodUrls.put("双色球",  "http://caipiao.163.com/help/14/0626/15/9VM6M0GT00754KN4_2.html");
        joinMethodUrls.put("福彩3D",  "http://caipiao.163.com/help/14/0626/16/9VM94FTF00754KN4_2.html");
        joinMethodUrls.put("大乐透",  "http://caipiao.163.com/help/14/0626/19/9VMJ9SNH00754KN4_2.html");
        joinMethodUrls.put("七星彩",  "http://caipiao.163.com/help/14/0627/10/9VO6LSTN00754KN4_2.html");
        joinMethodUrls.put("七乐彩",  "http://caipiao.163.com/help/14/0627/10/9VO70C3O00754KN4_2.html");
        joinMethodUrls.put("排列3",  "http://caipiao.163.com/help/14/0627/10/9VO7NNQF00754KN4_2.html");
        joinMethodUrls.put("排列5",  "http://caipiao.163.com/help/14/0627/11/9VO8PB7F00754KN4_2.html");
        joinMethodUrls.put("15选5",  "http://caipiao.163.com/help/14/0627/12/9VOCNUHO00754KN4_2.html");
        return joinMethodUrls;
    }
}
