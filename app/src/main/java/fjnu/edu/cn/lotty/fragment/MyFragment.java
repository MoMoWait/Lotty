package fjnu.edu.cn.lotty.fragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import fjnu.edu.cn.lotty.R;
import fjnu.edu.cn.lotty.activity.AboutActivity;
import fjnu.edu.cn.lotty.activity.LoginActivity;
import fjnu.edu.cn.lotty.activity.SuggestionActivity;
import fjnu.edu.cn.lotty.base.AppBaseFragment;
import fjnu.edu.cn.lotty.data.ConstData;
import momo.cn.edu.fjnu.androidutils.utils.DialogUtils;
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
        if(mIsLogin)
            mTextLogin.setText("GaoFei");
        mImgHeadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mIsLogin){
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
            ToastUtils.showToast("登录成功");
            mIsLogin = true;
            mTextLogin.setText("GaoFei");
        }
    }
}
