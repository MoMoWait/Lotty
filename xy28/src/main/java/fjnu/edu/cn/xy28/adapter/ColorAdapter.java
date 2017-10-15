package fjnu.edu.cn.xy28.adapter;

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

import fjnu.edu.cn.xy28.R;
import fjnu.edu.cn.xy28.bean.ColorInfo;

/**
 * Created by Administrator on 2017\9\5 0005.
 * 开奖列表适配器
 */

public class ColorAdapter extends ArrayAdapter<ColorInfo> {
    //private List<AllColorInfo> mObjects;
    private LayoutInflater mInflater;
    private int mResourceId;
    public ColorAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<ColorInfo> objects) {
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
        ColorInfo itemColorInfo = getItem(position);
        TextView oneColorText = itemView.findViewById(R.id.text_time);
        TextView twoColorText = itemView.findViewById(R.id.text_issueno);
        TextView threeColorText = itemView.findViewById(R.id.text_number);
        if(itemColorInfo != null){
            oneColorText.setText(itemColorInfo.getOpenDate());
            twoColorText.setText(itemColorInfo.getIssueNo());
            threeColorText.setText(itemColorInfo.getNumber());
        }

        return itemView;

    }
}
