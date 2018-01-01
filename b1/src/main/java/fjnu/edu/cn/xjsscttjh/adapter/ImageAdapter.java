package fjnu.edu.cn.xjsscttjh.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.xutils.x;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import momo.cn.edu.fjnu.androidutils.utils.DeviceInfoUtils;

/**
 * Created by GaoFei on 2017/12/31.
 * 轮播图效果适配器
 */

public class ImageAdapter extends PagerAdapter {

    private List<String> mImgUrls;
    private Map<String, ImageView> mViews = new HashMap<>();
    private Context mContext;
    private ImageView mCurrView;
    public ImageAdapter(Context context, List<String> imgUrls){
        mImgUrls = imgUrls;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mImgUrls.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //if(mCurrView != null && mCurrView.getParent() != null)
         //   (ViewGroup)mCurrView.getParent().removeView(mCurrView);
        String imgUrl =  mImgUrls.get(position);
        ImageView itemView =  mViews.get(imgUrl);
        if(itemView == null){
            itemView = new ImageView(mContext);
            itemView.setScaleType(ImageView.ScaleType.FIT_XY);
            x.image().bind(itemView, imgUrl);
            mViews.put(imgUrl, itemView);
        }
        container.addView(itemView);
        ViewGroup.LayoutParams params = itemView.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = DeviceInfoUtils.getScreenWidth(mContext) / 3;
        itemView.setLayoutParams(params);
        mCurrView = itemView;
        return mCurrView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       ImageView itemView =  mViews.get(mImgUrls.get(position));
       if(itemView != null){
          container.removeView(itemView);
       }
    }
}
