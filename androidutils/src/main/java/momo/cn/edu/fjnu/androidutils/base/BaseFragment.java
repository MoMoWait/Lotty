package momo.cn.edu.fjnu.androidutils.base;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;

import java.io.File;

import momo.cn.edu.fjnu.androidutils.data.RequestCodeForActivity;

/**
 * 基本的Fragment
 * Created by GaoFei on 2016/1/3.
 */
public abstract class BaseFragment extends Fragment {

    private String mCropFilePath;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    public abstract void init();

    public void startCamera(String rawPath){
        File rawFile = new File(rawPath);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(rawFile));
        startActivityForResult(intent, RequestCodeForActivity.RQC_CAMERA);
    }

    // 裁剪图片
    public void startCropPhoto(Uri uri) {

        //if(Environment.getExternalStorageState().equals(m))
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            mCropFilePath = new File(Environment.getExternalStorageDirectory(),"momo_cn_edu_fjnu.jpg").getAbsolutePath();
            // 裁剪图片
            Uri imageUri =Uri.fromFile(new File(mCropFilePath));
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/*");
            // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
            intent.putExtra("crop", "true");
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            // outputX outputY 是裁剪图片宽高
            //intent.putExtra("outputX", 256);
            //intent.putExtra("outputY", 256);
            intent.putExtra("scale", true);
            intent.putExtra("return-data", false);
            intent.putExtra("output", imageUri);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            intent.putExtra("noFaceDetection", false);
            startActivityForResult(intent, RequestCodeForActivity.RQC_CROP_PHOTO);
        }


    }

    public void startSelectPhoto(){
        //启动图片搜索
        Intent intent = new Intent();
        intent.setType("image/*");//打开图片方式
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, RequestCodeForActivity.RQC_SELECT_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == RequestCodeForActivity.RQC_CAMERA)
                onTakePicture();
            else if(requestCode == RequestCodeForActivity.RQC_SELECT_PHOTO){
                Uri localUri = data.getData();
                String[] projStrings = { MediaStore.Images.Media.DATA };
                Cursor cursor = getActivity().managedQuery(localUri, projStrings, null, null,
                        null);
                int cloum_index = cursor
                        .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String pathString = cursor.getString(cloum_index);
               // startPhotoZoom();
                onSelectPhoto(Uri.fromFile(new File(pathString)));
            }else if(requestCode == RequestCodeForActivity.RQC_CROP_PHOTO){
                onPhotoCrop(mCropFilePath);
            }

        }
    }

    /**
     * 拍照回调函数
     */
    public void onTakePicture(){
    }

    /**
     * 选择图片回调函数
     */
    public void onSelectPhoto(Uri uri){

    }

    /***
     * 裁剪图片回调函数
     */
    public void onPhotoCrop(String filePath){

    }
}
