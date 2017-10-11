package cn.edu.fjnu.stockhelp.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;

import momo.cn.edu.fjnu.androidutils.base.BaseFragment;

/**
 * Created by gaofei on 2017/10/11.
 */

public class AppBaseFragment extends BaseFragment {
    @Override
    public void init() {

    }

    public void showNetWorkErrorDialog(){
        if(getActivity() != null){
            new AlertDialog.Builder(getContext()).setCancelable(false).setTitle("温馨提示")
                    .setMessage("请检查网络").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    getActivity().finish();
                }
            }).show();
        }

    }
}
