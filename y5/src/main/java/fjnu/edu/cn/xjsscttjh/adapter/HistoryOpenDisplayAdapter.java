package fjnu.edu.cn.xjsscttjh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.bean.ColorInfo;
import momo.cn.edu.fjnu.androidutils.utils.SizeUtils;

/**
 * Created by gaofei on 2018/2/3.
 * 历史列表适配器
 */

public class HistoryOpenDisplayAdapter extends LotteryDisplayAdapter{
    private LayoutInflater mInflater;
    private int mResourceId;
    private Context mContext;
    public HistoryOpenDisplayAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<ColorInfo> objects) {
        super(context, resource, textViewResourceId, objects);
        mResourceId = resource;
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = null;
        if(convertView != null){
            itemView = convertView;
        }else{
            itemView = mInflater.inflate(mResourceId, parent, false);
        }
        ColorInfo itemColorInfo = getItem(position);
        TextView oneColorText = (TextView) itemView.findViewById(R.id.text_time);
        TextView twoColorText = (TextView) itemView.findViewById(R.id.text_issueno);
        LinearLayout layoutLuckyNumber = (LinearLayout) itemView.findViewById(R.id.layout_lucky_number);
        if(itemColorInfo != null){
            oneColorText.setText(itemColorInfo.getOpenDate() + "  " + itemColorInfo.getIssueNo());
            //twoColorText.setText(itemColorInfo.getIssueNo());
            //oneColorText.setText(itemColorInfo.getIssueNo());
            //itemColorInfo.getOpenDate()
            //threeColorText.setText("开奖号：" + itemColorInfo.getNumber());
            layoutLuckyNumber.removeAllViews();
            String[] numbers = itemColorInfo.getNumber().split("\\s+");
            for(String itemNumber: numbers){
                TextView luckyNumberView = new TextView(mContext);
                luckyNumberView.setBackgroundResource(R.drawable.luck_number_back_red);
                luckyNumberView.setTextColor(mContext.getResources().getColor(R.color.white));
                luckyNumberView.setGravity(Gravity.CENTER);
                luckyNumberView.setText(itemNumber);
                luckyNumberView.setTextSize(15);

                layoutLuckyNumber.addView(luckyNumberView);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)luckyNumberView.getLayoutParams();
                params.leftMargin = SizeUtils.dp2px(10);
            }
        }

        return itemView;
    }
}
