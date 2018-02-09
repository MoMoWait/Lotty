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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.bean.ForecastInfo;

/**
 * Created by gaofei on 2018/2/8.
 * 网易预测消息列表
 */

public class WyForecastInfoAdapter extends ArrayAdapter<ForecastInfo>{
    private int mResource;
    private Context mContext;
    public WyForecastInfoAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ForecastInfo> objects) {
        super(context, resource, objects);
        mResource = resource;
        mContext = context;
    }


    @Override
    public int getItemViewType(int position) {
        return 1;
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
            holder.textTime = itemView.findViewById(R.id.text_time);
            holder.textTitle = itemView.findViewById(R.id.text_title);
            itemView.setTag(holder);
        }
        Object itemObject = getItem(position);
        holder.textTime.setVisibility(View.VISIBLE);
        holder.textTitle.setTextColor(mContext.getResources().getColor(R.color.black));
        holder.textTitle.setBackgroundColor(mContext.getResources().getColor(android.R.color.transparent));
        LinearLayout.LayoutParams titleParams = (LinearLayout.LayoutParams) holder.textTitle.getLayoutParams();
        titleParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        holder.textTitle.setGravity(Gravity.LEFT);
        ForecastInfo info = (ForecastInfo)itemObject;
        holder.textTime.setText(info.getTime());
        holder.textTitle.setText(info.getTitle());
        return  itemView;
    }

    class ViewHolder{
        TextView textTime;
        TextView textTitle;
    }
}
