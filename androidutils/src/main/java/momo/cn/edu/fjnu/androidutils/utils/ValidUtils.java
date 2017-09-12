package momo.cn.edu.fjnu.androidutils.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证工具
 */
public class ValidUtils {

	
	private ValidUtils(){
	}


	/**
	 * 判断字符串是否为空
	 * @param str  待检验字符串
	 * @return
	 */
	public static boolean isEmpty(String str){
		return isEmpty(str, false);
	}

	/**
	 * 判断字符串是否为空
	 * @param str          待检验字符串
	 * @param isTrim     是否清空空格
	 * @return
	 */
	public static boolean isEmpty(String str,boolean isTrim){
		if(str == null)
			return true;
		if(isTrim){
			return "".equals(str.trim());
		}else{
			return "".equals(str);
		}
	}

	/**
	 * 判断字符串数组是否为空
	 * @param params  待检验数组
	 * @return
	 */
	public static boolean isEmpty(String...params){
		
		return isEmpty(false, params);
	}


	/**
	 * 判断字符串数组是否为空，只要有一个为空即为空
	 * @param isTrim     是否清空空白字符
	 * @param params   数组参数
	 * @return
	 */
	public static boolean isEmpty(boolean isTrim,String...params){
		
		if(params == null||params.length==0)
			return true;
		for(String param:params){
			if(isEmpty(param, isTrim))
				return true;
		}
		return false;
	}


	/**
	 * 判断当前字符串是否是电话号码
	 * @param phoneNum  待检验字符串
	 * @return
	 */
	public static boolean isPhoneNumber(String phoneNum){
		if(isEmpty(phoneNum, true))
			return false;
		Pattern pattern = Pattern.compile("^1\\d{10}$");
		Matcher matcher = pattern.matcher(phoneNum.trim());
		return matcher.matches();
	}

	/**
	 * 判断当前字符串是否是邮箱
	 * @param email  待检验字符串
	 * @return
	 */
	public static boolean isEmail(String  email){
		if(isEmpty(email, true))
			return false;
		Pattern pattern = Pattern.compile("^([a-z]|[A-Z]|[0-9])+@([a-z]|[A-Z]|[0-9])+\\.([a-z]|[A-Z]){2,}$");
		Matcher matcher = pattern.matcher(email.trim());
		return matcher.matches();
	}


	/**
	 * 判断当前字符串是否符合IP特征
	 * @param ip  待检验字符串
	 * @return
	 */
	public static boolean isIP(String ip){
		if(isEmpty(ip, true))
			return false;
		Pattern pattern = Pattern.compile("(^\\d{1,3}\\.){3}\\d{1,3}$");
		Matcher matcher = pattern.matcher(ip.trim());
		return matcher.matches();
	}
}
