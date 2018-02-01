package fjnu.edu.cn.xjsscttjh.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import fjnu.edu.cn.xjsscttjh.data.ConstData;

/**
 * Created by Administrator on 2017/11/23.
 * 工具Activity
 */

public class ToolActivity extends SingleFragmentActivity {

    private String mTargetFragmentName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mTargetFragmentName = getIntent().getStringExtra(ConstData.IntentKey.TARGET_FRAGMENT);
        super.onCreate(savedInstanceState);
    }

    @Override
    public Fragment createFragment() {
        try {
            return (Fragment) Class.forName(mTargetFragmentName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
