package fjnu.edu.cn.beijingquickthree.fragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import fjnu.edu.cn.beijingquickthree.R;
import fjnu.edu.cn.beijingquickthree.activity.AboutActivity;
import fjnu.edu.cn.beijingquickthree.activity.LoginActivity;
import fjnu.edu.cn.beijingquickthree.activity.SuggestionActivity;
import fjnu.edu.cn.beijingquickthree.base.AppBaseFragment;
import fjnu.edu.cn.beijingquickthree.data.ConstData;
import momo.cn.edu.fjnu.androidutils.utils.DialogUtils;
import momo.cn.edu.fjnu.androidutils.utils.StorageUtils;
import momo.cn.edu.fjnu.androidutils.utils.ToastUtils;

/**
 * Created by gaofei on 2017/9/9.
 * 我的页面
 */
@ContentView(R.layout.fragment_my)
public class MyFragment extends AppBaseFragment {

    @ViewInject(R.id.img_head_photo)
    private ImageView mImgHeadPhoto;

    @ViewInject(R.id.layout_about)
    private LinearLayout mLayoutAbout;

    @ViewInject(R.id.layout_suggestion)
    private LinearLayout mLayoutSuggestion;

    @ViewInject(R.id.layout_information)
    private LinearLayout mLayoutInformation;

    @ViewInject(R.id.layout_update)
    private LinearLayout mLayoutUpdate;

    @ViewInject(R.id.text_login)
    private TextView mTextLogin;

    private boolean mIsLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }


    @Override
    public void init(){
        mIsLogin = !TextUtils.isEmpty(StorageUtils.getDataFromSharedPreference(ConstData.IntentKey.USER_NAME));
        if(mIsLogin)
            mTextLogin.setText(StorageUtils.getDataFromSharedPreference(ConstData.IntentKey.USER_NAME));
        mImgHeadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mIsLogin){
                    new AlertDialog.Builder(getContext()).setTitle("温馨提示").setMessage("退出当前帐号?").setCancelable(false)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    mIsLogin = false;
                                    mTextLogin.setText(getString(R.string.click_login));
                                    StorageUtils.saveDataToSharedPreference(ConstData.IntentKey.USER_NAME, "");
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();
                    return;
                }
                startActivityForResult(new Intent(getActivity(), LoginActivity.class), 1000);
            }
        });

        mLayoutAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity()).setTitle(getString(R.string.about_we))
                        .setMessage(ConstData.ABOUT_MESSAGE).setPositiveButton("确定", null).show();
            }
        });

        mLayoutInformation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AboutActivity.class));
            }
        });

        mLayoutUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DialogUtils.showLoadingDialog(getActivity(), false);
                new AsyncTask<String, Integer, Integer>(){
                    @Override
                    protected Integer doInBackground(String... strings) {
                        try {
                            java.util.concurrent.TimeUnit.MILLISECONDS.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Integer integer) {
                        DialogUtils.closeLoadingDialog();
                        ToastUtils.showToast("当前为最新版本");
                    }
                }.execute();
            }
        });

        mLayoutSuggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SuggestionActivity.class));
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1000 && resultCode == Activity.RESULT_OK){
            ToastUtils.showToast(getString(R.string.login_succ));
            mIsLogin = true;
            String userName = data.getStringExtra(ConstData.IntentKey.USER_NAME);
            StorageUtils.saveDataToSharedPreference(ConstData.SharedKey.USER_NAME, userName);
            mTextLogin.setText(userName);
        }
    }
}
