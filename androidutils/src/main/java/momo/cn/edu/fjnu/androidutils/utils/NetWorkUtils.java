package momo.cn.edu.fjnu.androidutils.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 网络操作工具
 * Created by GaoFei on 2016/1/3.
 */
public class NetWorkUtils {
    private NetWorkUtils(){

    }

    /**
     * 判断网络是否可用
     * @param context
     * @return
     */
    public static boolean haveInternet(Context context){
        ConnectivityManager connectivityManager=(ConnectivityManager)(context.getSystemService(Context.CONNECTIVITY_SERVICE));
        if(connectivityManager==null)
            return false;
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null&&networkInfo.isConnected())
            return true;
        return false;
    }


    /**
     * 获取当前使用网络的IP地址
     * @return
     */
    public static String getLocalIpAddress()
    {
        try
        {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
            {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
                {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()&&!inetAddress.isLinkLocalAddress())
                    {
                        return inetAddress.getHostAddress();
                        // return inetAddress.getHostAddress();
                    }
                }
            }
        }
        catch (SocketException ex)
        {
            Log.i(" Ip地址获取出错", ex.toString());
        }
        return null;
    }
    /**
     * @deprecated
     * 判断WIFI是否可用
     * @param context
     * @return
     */
    public static boolean isWIFIActive(Context context){
        ConnectivityManager connectivityManager=(ConnectivityManager)(context.getSystemService(Context.CONNECTIVITY_SERVICE));
        if(connectivityManager==null)
            return false;
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo!= null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
            return true;
        return false;
    }
    /**
     *(WIFI可用时可获取)
     * 获取IP地址
     */
    public static String getWifiIP(Context context){
        WifiManager wifiManager=(WifiManager)(context.getSystemService(Context.WIFI_SERVICE));
        WifiInfo wInfo=wifiManager.getConnectionInfo();
        return intToIp(wInfo.getIpAddress());
        //return wInfo.getIpAddress();
    }
    /**
     *
     * 获取MAC地址
     */
    public static String getLocalMacAddress(Context context){
        WifiManager wifiManager=(WifiManager)(context.getSystemService(Context.WIFI_SERVICE));
        WifiInfo wInfo=wifiManager.getConnectionInfo();
        return wInfo.getMacAddress();
    }

    private static String intToIp(int i) {

        return (i & 0xFF ) + "." +
                ((i >> 8 ) & 0xFF) + "." +
                ((i >> 16 ) & 0xFF) + "." +
                ( i >> 24 & 0xFF) ;
    }
}
