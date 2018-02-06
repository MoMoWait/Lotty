package fjnu.edu.cn.xjsscttjh.bean;
import android.support.v4.app.Fragment;

/**
 * Created by gaofei on 2018/2/5.
 * 导航栏选项
 */

public class TabItem {
    /**
     * 导航栏文字
     */
    private String title;
    /**
     * 正常图片
     */
    private int normalImg;
    /**
     * 选中图片
     */
    private int selectImg;
    /**
     * 正常文字颜色
     */
    private int normalTextColor;
    /**
     * 选中的文字颜色
     */
    private int selectTextColor;
    /**
     * 当前的Fragment
     */
    private Class<? extends Fragment> fragmentClass;

    public TabItem(){

    }

    public TabItem(String title, int normalImg, int selectImg, int normalTextColor, int selectTextColor, Class<? extends Fragment> fragmentClass) {
        this.title = title;
        this.normalImg = normalImg;
        this.selectImg = selectImg;
        this.normalTextColor = normalTextColor;
        this.selectTextColor = selectTextColor;
        this.fragmentClass = fragmentClass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNormalImg() {
        return normalImg;
    }

    public void setNormalImg(int normalImg) {
        this.normalImg = normalImg;
    }

    public int getSelectImg() {
        return selectImg;
    }

    public void setSelectImg(int selectImg) {
        this.selectImg = selectImg;
    }



    public Class<? extends Fragment> getFragmentClass() {
        return fragmentClass;
    }

    public void setFragmentClass(Class<? extends Fragment> fragmentClass) {
        this.fragmentClass = fragmentClass;
    }

    public int getNormalTextColor() {
        return normalTextColor;
    }

    public void setNormalTextColor(int normalTextColor) {
        this.normalTextColor = normalTextColor;
    }

    public int getSelectTextColor() {
        return selectTextColor;
    }

    public void setSelectTextColor(int selectTextColor) {
        this.selectTextColor = selectTextColor;
    }
}
