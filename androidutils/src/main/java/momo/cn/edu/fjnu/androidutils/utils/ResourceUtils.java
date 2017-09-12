package momo.cn.edu.fjnu.androidutils.utils;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Html;

import java.io.InputStream;

import momo.cn.edu.fjnu.androidutils.data.CommonValues;

/**
 * 获取应用程序资源
 */
public class ResourceUtils {

	private ResourceUtils(){
	}

	/**
	 * 获取字符串资源
	 * @param strID
	 * @return
	 */
	public static String getString(int strID){
		
		return CommonValues.application.getResources().getString(strID);
	}

	/**
	 * 获取字符串数组
	 * @param arrayID
	 * @return
	 */
	public static String[]  getStringArray(int arrayID){
		return CommonValues.application.getResources().getStringArray(arrayID);
	}

	/**
	 * 获取整形数组
	 * @param arrayID
	 * @return
	 */
	public static int[] getIntegerArray(int arrayID){
		return CommonValues.application.getResources().getIntArray(arrayID);
	}

	/**
	 * 获取Drawable资源
	 * @param drawableID
	* @return
	 * */
	public static Drawable getDrawabel(int drawableID){
		return ContextCompat.getDrawable(CommonValues.application, drawableID);
	}

	/**
	 *获取颜色
	 * @param colorID
	 * @return
	 */
	public static int getColor(int colorID){
		return ContextCompat.getColor(CommonValues.application, colorID);
	}


	/**
	 * 获取尺寸资源
	 * @param dimenID
	 * @return
	 */
	public static float getDimension(int dimenID){
		return CommonValues.application.getResources().getDimension(dimenID);
	}

	/**
	 * 读取raw文件中至输入流
	 * @param rawID
	 * @return
	 */
	public static InputStream getInputStreamFromRaw(int rawID){
		
		return CommonValues.application.getResources().openRawResource(rawID);
	}

	/**
	 * 从字符串解析并得到HTML字符串
	 * @param content
	 * @return
	 */
	public static CharSequence getHtmlText(String content){
		return Html.fromHtml(content);
	}
	
}
