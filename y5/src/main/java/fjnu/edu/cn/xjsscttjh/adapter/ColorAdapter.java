package fjnu.edu.cn.xjsscttjh.adapter;

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

import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.bean.ColorInfo;

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
        TextView oneColorText = (TextView) itemView.findViewById(R.id.text_time);
        TextView twoColorText = (TextView) itemView.findViewById(R.id.text_issueno);
        TextView threeColorText = (TextView) itemView.findViewById(R.id.text_number);
        if(itemColorInfo != null){
            oneColorText.setText("时间：" + itemColorInfo.getOpenDate());
            twoColorText.setText("期号：" + itemColorInfo.getIssueNo());
            threeColorText.setText("开奖号：" + itemColorInfo.getNumber());
        }

        return itemView;

    }
}
