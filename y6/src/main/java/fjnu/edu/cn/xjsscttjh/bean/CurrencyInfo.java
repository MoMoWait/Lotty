package fjnu.edu.cn.xjsscttjh.bean;

/**
 * Created by Administrator on 2017/11/24.
 * 币种信息
 */

public class CurrencyInfo {
    private String unit;
    private String name;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
