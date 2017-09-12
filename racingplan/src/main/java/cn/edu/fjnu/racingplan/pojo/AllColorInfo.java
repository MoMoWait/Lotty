package cn.edu.fjnu.racingplan.pojo;

/**
 * Created by Administrator on 2017\9\5 0005.
 * 对应开奖列表的每一个项
 */

public class AllColorInfo {
    private ColorInfo oneColor;
    private ColorInfo twoColor;
    private ColorInfo threeColor;

    public ColorInfo getOneColor() {
        return oneColor;
    }

    public void setOneColor(ColorInfo oneColor) {
        this.oneColor = oneColor;
    }

    public ColorInfo getTwoColor() {
        return twoColor;
    }

    public void setTwoColor(ColorInfo twoColor) {
        this.twoColor = twoColor;
    }

    public ColorInfo getThreeColor() {
        return threeColor;
    }

    public void setThreeColor(ColorInfo threeColor) {
        this.threeColor = threeColor;
    }
}
