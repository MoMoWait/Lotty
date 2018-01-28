package momo.cn.edu.fjnu.androidutils.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

import momo.cn.edu.fjnu.androidutils.data.CommonValues;

/**
 * 获取应用包工具
 */
public class PackageUtils {

	private PackageUtils(){
		
	}

	/**
	 * 获取设备所有已安装的应用程序(除了系统APP)
	 * @param context
	 * @return
	 */
	public static List<PackageInfo> getAllApp(Context context){
		PackageManager pm=context.getPackageManager();
		List<PackageInfo> packageInfos=pm.getInstalledPackages(0);
		List<PackageInfo> results=new ArrayList<>();
		for(PackageInfo packageInfo:packageInfos){
			if((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)==0){
				results.add(packageInfo);
			}
		}
		return results;
	}

	/**
	 * 获取包名
	 * @return
	 */
	public static String getPackageName(){
		return CommonValues.application.getPackageName();
	}

	/**
	 * 判断某个包名的APK是否安装
	 * @param pkgName
	 * @return
	 */
	public static boolean isInstalled(Context context, String pkgName){
		PackageManager pm = context.getPackageManager();
		try {
			 pm.getPackageInfo(pkgName, 0);
			 return true;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
}
