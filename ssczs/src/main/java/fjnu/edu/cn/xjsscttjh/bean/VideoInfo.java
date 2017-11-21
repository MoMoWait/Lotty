package fjnu.edu.cn.xjsscttjh.bean;

/**
 * Created by gaofei on 2017/10/9.
 * 视频项
 */

public class VideoInfo {
    private String title;
    private String vidoeUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVidoeUrl() {
        return vidoeUrl;
    }

    public void setVidoeUrl(String vidoeUrl) {
        this.vidoeUrl = vidoeUrl;
    }

    @Override
    public String toString() {
        return title;
    }
}
