package cn.edu.fjnu.racingplan.data;

/**
 * Created by Administrator on 2017\9\2 0002.
 * 常量数据
 */

public class ConstData {
    public static final String APP_LOAD_URL = "http://ovjj5kn8p.bkt.clouddn.com/HouTai.txt";
    public static final String APP_CONTEN_URL = "http://oxj8dd2io.bkt.clouddn.com/PCDDQJ.text";
    public static final String HEADER_INFO_URL = "http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300210&src=0000100001%7C6000003060";
    public static final String COLOR_INFO_URL = "http://m.zhcw.com/clienth5.do?transactionType=8020&busiCode=300204&src=0000100001%7C6000003060";
    public static final String WELFARE_INFO_URL = "http://m.zhcw.com/clienth5.do?transactionType=8020&busiCode=300206&src=0000100001%7C6000003060";
    public static final String POLICY_INFO_URL = "http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300210&src=0000100001%7C6000003060";
    public static final String REWARD_INFO_URL = "http://m.zhcw.com/clienth5.do?transactionType=300304&src=0000100001%7C6000003060";
    public static final String ONE_COLOR_URL = "http://m.13322.com/mlottery/core/detailedLotteryResults.findPK10DetailedResults.do?_=1503539785.201749&appType=1&appno=11&deviceId=072FAF2EA6AC41F0896CA6FE1489669C&deviceToken=&lang=zh&loginToken=&sign=1cc9c9759d04cf9768dcda2a58b9d74403&timeZone=8&userId=&version=240";
    public static final String TWO_COLOR_URL = "http://api.jisuapi.com/caipiao/history?appkey=debc0bd758c77fe1&caipiaoid=11&num=20";
    public static final String THREE_COLOR_URL = "http://api.jisuapi.com/caipiao/history?appkey=debc0bd758c77fe1&caipiaoid=3&num=20";

    public static final long INIT_TIME = 2000;
    public interface TaskResult{
        int SUCC = 0;
        int FAILED = -1;
    }
    public interface IntentKey{
        String WEB_LOAD_URL = "web_load_url";
        String IS_INFORMATION_URL = "is_information_url";
    }
}
