package fjnu.edu.cn.xjsscttjh.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xutils.x;

import java.util.List;

import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.bean.LotteryInfo;
import fjnu.edu.cn.xjsscttjh.bean.NowOpenInfo;
import momo.cn.edu.fjnu.androidutils.utils.SizeUtils;

/**
 * Created by gaofei on 2017/11/30.
 * 开奖列表适配器
 */

public class NowOpenAdapter extends ArrayAdapter<NowOpenInfo> {
    private int mResource;
    private Context mContext;
    public NowOpenAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<NowOpenInfo> objects) {
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
        ViewHolder holder = (ViewHolder) itemView.getTag();
        if(holder == null){
            holder = new ViewHolder();
            holder.imgLotty = itemView.findViewById(R.id.img_lotty);
            holder.textLottyTitle = itemView.findViewById(R.id.text_lotty_title);
            holder.textLottyNo = itemView.findViewById(R.id.text_lotty_no);
            holder.textLottyDate = itemView.findViewById(R.id.text_lotty_date);
            holder.layoutLottyNumber = itemView.findViewById(R.id.layout_lotty_number);
            itemView.setTag(holder);
        }
        NowOpenInfo info = getItem(position);
        x.image().bind(holder.imgLotty, info.getImgUrl());
        holder.textLottyTitle.setText(info.getTitle());
        holder.textLottyNo.setText(info.getNo());
        holder.textLottyDate.setText(info.getOpenDate());
        String[] numbers = info.getNumber().split("\\s+");
        holder.layoutLottyNumber.removeAllViews();
        for(String itemNumber : numbers){
            //添加中奖号码
            TextView numberView = new TextView(mContext);
            numberView.setText(itemNumber);
            numberView.setTextColor(mContext.getResources().getColor(R.color.white));
            numberView.setTextSize(15);
            numberView.setGravity(Gravity.CENTER);
            numberView.setBackgroundResource(R.drawable.luck_number_back_red);
            holder.layoutLottyNumber.addView(numberView);
            LinearLayout.LayoutParams numberParams = (LinearLayout.LayoutParams)numberView.getLayoutParams();
            numberParams.leftMargin = SizeUtils.dp2px(6);

        }

        //LotteryInfo itemLotteryInfo = getItem(position);
        //imgLotteryType.setImageResource(itemLotteryInfo.getImgRes());
        //textLotteryName.setText(itemLotteryInfo.getDes());
        return  itemView;
    }

    class ViewHolder{
        ImageView imgLotty;
        TextView textLottyTitle;
        TextView textLottyNo;
        TextView textLottyDate;
        LinearLayout layoutLottyNumber;
    }
}
