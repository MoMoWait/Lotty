package fjnu.edu.cn.xjsscttjh.bean;

/**
 * Created by gaofei on 2018/1/28.
 * 预测信息
 */

public class ForecastInfo {
    /**
     * 标题
     */
    private String title;
    /**
     * 时间
     */
    private String time;
    /**
     * 跳转地址
     */
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ForecastInfo{" +
                "title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
