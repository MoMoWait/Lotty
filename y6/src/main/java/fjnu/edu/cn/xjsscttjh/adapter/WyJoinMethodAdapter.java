package fjnu.edu.cn.xjsscttjh.adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.bean.TrendInfo;

/**
 * Created by gaofei on 2018/2/8.
 * 网易彩票玩法介绍适配器
 */

public class WyJoinMethodAdapter extends ArrayAdapter<String> {
    private int mResource;
    private Context mContext;
    private int mTextResourceId;
    public WyJoinMethodAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<String> objects) {
        super(context, resource, textViewResourceId, objects);
        mTextResourceId = textViewResourceId;
        mContext = context;
        mResource = resource;
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
            holder.itemText = (TextView) itemView.findViewById(mTextResourceId);
            itemView.setTag(holder);
        }
        String text = getItem(position);
        //x.image().bind(holder.itemImg, itemInfo.getImgUrl());
        holder.itemText.setText(text);
        return  itemView;
    }

    class ViewHolder{
        TextView itemText;
    }
}
