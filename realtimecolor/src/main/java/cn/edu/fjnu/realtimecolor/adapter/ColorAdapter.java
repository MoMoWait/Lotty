package cn.edu.fjnu.realtimecolor.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import cn.edu.fjnu.realtimecolor.R;
import cn.edu.fjnu.realtimecolor.pojo.AllColorInfo;

/**
 * Created by Administrator on 2017\9\5 0005.
 * 开奖列表适配器
 */

public class ColorAdapter extends ArrayAdapter<AllColorInfo> {
    //private List<AllColorInfo> mObjects;
    private LayoutInflater mInflater;
    private int mResourceId;
    public ColorAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<AllColorInfo> objects) {
        super(context, resource, textViewResourceId, objects);
       // mObjects = objects;
        mResourceId = resource;
        mInflater = LayoutInflater.from(context);
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
        AllColorInfo itemColorInfo = getItem(position);
        TextView oneColorText = itemView.findViewById(R.id.text_one_color);
        TextView twoColorText = itemView.findViewById(R.id.text_two_color);
        TextView threeColorText = itemView.findViewById(R.id.text_three_color);
        if(itemColorInfo != null &&itemColorInfo.getOneColor() != null){
            oneColorText.setVisibility(View.VISIBLE);
            oneColorText.setText(itemColorInfo.getOneColor().toString());
        }else{
            oneColorText.setVisibility(View.GONE);
        }
        if(itemColorInfo != null &&itemColorInfo.getTwoColor()!= null){
            twoColorText.setVisibility(View.VISIBLE);
            twoColorText.setText(itemColorInfo.getTwoColor().toString());
        }else{
            twoColorText.setVisibility(View.GONE);
        }
        if(itemColorInfo != null &&itemColorInfo.getThreeColor() != null){
            threeColorText.setVisibility(View.VISIBLE);
            threeColorText.setText(itemColorInfo.getThreeColor().toString());
        }else{
            threeColorText.setVisibility(View.GONE);
        }
        return itemView;

    }
}
