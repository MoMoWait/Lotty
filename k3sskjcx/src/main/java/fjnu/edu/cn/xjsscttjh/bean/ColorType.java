package fjnu.edu.cn.xjsscttjh.bean;

/**
 * Created by Administrator on 2017/11/22.
 * 彩票类型
 */

public class ColorType {

    public ColorType(){

    }


    public ColorType(int colorId, String des) {
        this.colorId = colorId;
        this.des = des;
    }

    /**
     * 彩票ID
     */
    private int colorId;
    /**
     * 彩票描述
     */
    private String des;

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return des;
    }
}
