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

import org.xutils.image.ImageOptions;
import org.xutils.x;
import java.util.List;
import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.bean.Message;

/**
 * Created by gaofei on 2017/12/3.
 * 彩讯适配器
 */

public class MessageAdapter extends ArrayAdapter<Message>{
    private Context mContext;
    private LayoutInflater mInflater;
    private int mResource;
    public MessageAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Message> objects) {
        super(context, resource, objects);
        mResource = resource;
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Message itemMessage = getItem(position);
        View itemView ;
        if(convertView != null)
            itemView = convertView;
        else
            itemView = mInflater.inflate(mResource, parent, false);
        if(itemView.getTag() == null){
            ViewHolder holder = new ViewHolder();
            holder.imgPic = (ImageView) itemView.findViewById(R.id.img_head);
            holder.textDes = (TextView) itemView.findViewById(R.id.text_summary);
            itemView.setTag(holder);
        }
        ViewHolder itemHolder = (ViewHolder) itemView.getTag();
        x.image().bind(itemHolder.imgPic, itemMessage.getPicUrl());
        itemHolder.textDes.setText(itemMessage.getTitle());
        return  itemView;
    }

    class ViewHolder{
        public ImageView imgPic;
        public TextView textDes;
    }
}
