package fjnu.edu.cn.xjsscttjh.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import fjnu.edu.cn.xjsscttjh.bean.LotteryInfo;

/**
 * Created by gaofei on 2017/11/30.
 * 所有彩票类型适配器
 */

public class AllLotteryAdapter extends ArrayAdapter<LotteryInfo> {
    public AllLotteryAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull LotteryInfo[] objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
