package fjnu.edu.cn.xjsscttjh.bean;

import java.util.List;

/**
 * Created by gaofei on 2018/2/3.
 * 历史开奖数据
 */

public class HistoryOpenInfo {
    private String title;
    private List<ColorInfo> infos;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ColorInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<ColorInfo> infos) {
        this.infos = infos;
    }
}
