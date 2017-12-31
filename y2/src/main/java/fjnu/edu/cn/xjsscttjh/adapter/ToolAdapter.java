package fjnu.edu.cn.xjsscttjh.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import fjnu.edu.cn.xjsscttjh.R;
import fjnu.edu.cn.xjsscttjh.bean.ToolInfo;
import fjnu.edu.cn.xjsscttjh.data.ConstData;

/**
 * Created by Administrator on 2017/11/23.
 * 工具条目适配器
 */

public class ToolAdapter extends ArrayAdapter<ToolInfo> {
    private Context mContext;
    private int mResource;
    public ToolAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ToolInfo[] objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView;
        if(convertView != null)
            itemView = convertView;
        else{
            itemView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
            AdapterView.LayoutParams itemParams = itemView.getLayoutParams();
            itemParams.width = ConstData.SCREEN_WIDTH / 3;
            itemParams.height = ConstData.SCREEN_WIDTH / 3;
            itemView.setLayoutParams(itemParams);
        }
        TextView textDes = itemView.findViewById(R.id.text_des);
        ImageView imgTool = itemView.findViewById(R.id.img_head);
        ViewGroup.LayoutParams imgParams = imgTool.getLayoutParams();
        imgParams.width = ConstData.SCREEN_WIDTH / 6;
        imgParams.height = ConstData.SCREEN_WIDTH / 6;
        imgTool.setLayoutParams(imgParams);
        ToolInfo info = getItem(position);
        textDes.setText(info.getDes());
        imgTool.setImageResource(info.getImgID());
        return  itemView;
    }
}
