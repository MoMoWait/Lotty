package fjnu.edu.cn.xjsscttjh.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.bean.LotteryInfo;

/**
 * Created by gaofei on 2017/11/30.
 * 所有彩票类型适配器
 */

public class AllLotteryAdapter extends ArrayAdapter<LotteryInfo> {
    private int mResource;
    private Context mContext;
    public AllLotteryAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull LotteryInfo[] objects) {
        super(context, resource, objects);
        mResource = resource;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView;
        if(convertView != null)
            itemView = convertView;
        else
            itemView = LayoutInflater.from(mContext).inflate(mResource, parent ,false);
        ImageView imgLotteryType = itemView.findViewById(R.id.img_lottery_type);
        TextView textLotteryName = itemView.findViewById(R.id.text_lottery_name);
        LotteryInfo itemLotteryInfo = getItem(position);
        imgLotteryType.setImageResource(itemLotteryInfo.getImgRes());
        textLotteryName.setText(itemLotteryInfo.getDes());
        return  itemView;
    }
}
