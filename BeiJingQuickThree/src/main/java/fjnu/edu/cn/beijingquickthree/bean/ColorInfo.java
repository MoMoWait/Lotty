package fjnu.edu.cn.beijingquickthree.bean;

/**
 * Created by Administrator on 2017\9\5 0005.
 * 开奖信息
 */

public class ColorInfo {
    private String openDate;
    private String issueNo;
    private String number;

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getIssueNo() {
        return issueNo;
    }

    public void setIssueNo(String issueNo) {
        this.issueNo = issueNo;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return  String.format("%1$s  %2$s  %3$s", openDate, issueNo, number);
       // return openDate + "  " + issueNo + "  " + number;
    }
}
