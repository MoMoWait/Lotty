package momo.cn.edu.fjnu.androidutils.pojo;

/**
 * 设备信息类
 * Created by GaoFei on 2016/1/3.
 */
public class DeviceInfo {
    /**设备品牌,型号*/
    private String deviceBrand;
    /**设备ID*/
    private String deviceID;
    /**屏幕宽度*/
    private int screenHeight;
    /**屏幕宽度*/
    private int screenWidth;
    /**屏幕密度*/
    private float denstity;
    /**屏幕每一寸像素密度*/
    private float denstityDP;
    /**获取缩放的denstity*/
    private float scaleDenstity;

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public float getDenstity() {
        return denstity;
    }

    public void setDenstity(float denstity) {
        this.denstity = denstity;
    }

    public float getDenstityDP() {
        return denstityDP;
    }

    public void setDenstityDP(float denstityDP) {
        this.denstityDP = denstityDP;
    }

    public float getScaleDenstity() {
        return scaleDenstity;
    }

    public void setScaleDenstity(float scaleDenstity) {
        this.scaleDenstity = scaleDenstity;
    }

    public String toString() {
        return "DeviceInfo{" +
                "deviceBrand='" + deviceBrand + '\'' +
                ", deviceID='" + deviceID + '\'' +
                ", screenHeight=" + screenHeight +
                ", screenWidth=" + screenWidth +
                ", denstity=" + denstity +
                ", denstityDP=" + denstityDP +
                ", scaleDenstity=" + scaleDenstity +
                '}';
    }
}
