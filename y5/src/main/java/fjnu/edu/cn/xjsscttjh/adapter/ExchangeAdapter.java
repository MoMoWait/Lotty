package fjnu.edu.cn.xjsscttjh.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/11/23.
 * 币种列表适配器
 */

public class ExchangeAdapter extends ArrayAdapter {
    public ExchangeAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = super.getView(position, convertView, parent);
        TextView textView = (TextView) itemView.findViewById(android.R.id.text1);
        textView.setTextSize(18);
        return itemView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View dropDownView = super.getDropDownView(position, convertView, parent);
        TextView textView = (TextView) dropDownView.findViewById(android.R.id.text1);
        textView.setTextSize(18);
        return dropDownView;
    }
}
