package fjnu.edu.cn.xjsscttjh.bean;

/**
 * Created by Administrator on 2017/11/23.
 * 工具信息
 */

public class ToolInfo {
    private int imgID;
    private String des;
    /**
     * 目标Fragment的名字
     */
    private String targetClassName;
    /**
     * 目标Activity的Label
     */
    private String targetActivityLabel;
    public ToolInfo(){

    }

    public ToolInfo(int imgID, String des, String targetClassName, String targetActivityLabel){
        this.imgID = imgID;
        this.des = des;
        this.targetClassName = targetClassName;
        this.targetActivityLabel = targetActivityLabel;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTargetClassName() {
        return targetClassName;
    }

    public void setTargetClassName(String targetClassName) {
        this.targetClassName = targetClassName;
    }

    public String getTargetActivityLabel() {
        return targetActivityLabel;
    }

    public void setTargetActivityLabel(String targetActivityLabel) {
        this.targetActivityLabel = targetActivityLabel;
    }
}
