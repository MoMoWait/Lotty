package fjnu.edu.cn.xjsscttjh.bean;

/**
 * Created by gaofei on 2017/11/29.
 * 彩票项
 */

public class LotteryInfo extends ColorType{
    /**
     * 图片ID
     */
    private int imgRes;
    public LotteryInfo(int imgRes, int colorId, String des){
        super(colorId, des);
        this.imgRes = imgRes;
    }
    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

}
