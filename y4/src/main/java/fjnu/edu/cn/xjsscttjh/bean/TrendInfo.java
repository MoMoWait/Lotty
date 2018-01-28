package fjnu.edu.cn.xjsscttjh.bean;

import java.util.Map;

/**
 * Created by GaoFei on 2018/1/28.
 * 走势信息
 */

public class TrendInfo {
    /**彩票图标URL*/
    private String imgUrl;
    /**彩票名字*/
    private String name;
    /**走势名称-URL*/
    private Map<String, String> trendUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getTrendUrl() {
        return trendUrl;
    }

    public void setTrendUrl(Map<String, String> trendUrl) {
        this.trendUrl = trendUrl;
    }

    @Override
    public String toString() {
        return "TrendInfo{" +
                "imgUrl='" + imgUrl + '\'' +
                ", name='" + name + '\'' +
                ", trendUrl=" + trendUrl +
                '}';
    }
}
