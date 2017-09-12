package momo.cn.edu.fjnu.androidutils.utils;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

/**
 * 通过Intent启动第三方程序
 */
public class InnerActivityUtils {

	private InnerActivityUtils(){
		
	}

	/**
	 * 访问URL
	 * @param context
	 * @param url
	 */
	public static void accessUrl(Context context, String url){
		Intent intent=new Intent(Intent.ACTION_VIEW);
		//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setData(Uri.parse(url));
		context.startActivity(intent);
	}

	/**
	 * 拨打电话号码
	 * @param context
	 * @param phoneNumber
	 */
	public static void dialPhoneNumber(Context context,String phoneNumber){
		Intent intent=new Intent(Intent.ACTION_DIAL);
		intent.setData(Uri.parse("tel:"+phoneNumber));
		context.startActivity(intent);
	}

	/**
	 * 搜索content关键字对应的内容
	 * @param context
	 * @param content
	 */
	public static void searchContent(Context context,String content){
		Intent intent=new Intent(Intent.ACTION_WEB_SEARCH);
		intent.putExtra(SearchManager.QUERY, content);
		context.startActivity(intent);
	}

	/**
	 * 启动邮件发送程序
	 * @param context
	 * @param receiver
	 */
	public static void sendEmail(Context context,String  receiver){
		Intent intent=new Intent(Intent.ACTION_SENDTO);
		intent.setData(Uri.parse( "mailto:"+receiver));
		context.startActivity(intent);
	}

	/**
	 * 从应用市场中搜索某个应用
	 * @param context
	 * @param packName
	 * @param notFoundError
	 */
	public static void searchAppFromMarket(Context context,String packName,int notFoundError){
		
		Uri uri=Uri.parse("market://search?q=pname:"+packName);
		Intent intent=new Intent(Intent.ACTION_VIEW);
		intent.setData(uri);
		PackageManager pManager=context.getPackageManager();
		if(intent.resolveActivity(pManager)!=null)
			context.startActivity(intent);
		else{
			ToastUtils.showToast(context.getResources().getString(notFoundError), Toast.LENGTH_SHORT);
		}

	}
}
