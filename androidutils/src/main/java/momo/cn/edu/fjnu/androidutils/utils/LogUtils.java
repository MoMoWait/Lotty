package momo.cn.edu.fjnu.androidutils.utils;
import android.util.Log;
/**
 * Created by gaofei on 16/10/6.
 * 日志,调试工具
 */

public class LogUtils {

    private static boolean mBVerboseSwitch = true;

    private static boolean mBDebugSwithch = true;

    private static boolean mBInfoSwitch = true;

    private static boolean mBWarningSwitch = true;

    private static boolean mBErrorSwitch = true;

    private static boolean mBDetailSwitch = true;

    private static final int DEFAULT_STACK_DEPTH = 4;

    private static int mPrintStackPath = DEFAULT_STACK_DEPTH;
    /**
     * 构造函数私有,防止实例化
     */
    private LogUtils(){

    }


    private static String buildMsg(String msg)
    {
        StringBuilder buffer = new StringBuilder();

        if (mBDetailSwitch)
        {
            final StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[mPrintStackPath];

            buffer.append("[ ");
            buffer.append(stackTraceElement.getFileName());
            buffer.append(": ");
            buffer.append(stackTraceElement.getLineNumber());
            buffer.append("]");
        }

        buffer.append(msg);

        return buffer.toString();
    }


    public static void v(String strTag, String strLog)
    {
        if (mBVerboseSwitch)
        {
            Log.v(strTag, buildMsg(strLog));
        }
    }

    public static void d(String strTag, String strLog)
    {
        if (mBDebugSwithch)
        {
            Log.d(strTag, buildMsg(strLog));
        }
    }

    public static void i(String strTag, String strLog)
    {
        if (mBInfoSwitch)
        {
            Log.i(strTag, buildMsg(strLog));
        }
    }

    public static void w(String strTag, String strLog)
    {
        if (mBWarningSwitch)
        {
            Log.w(strTag, buildMsg(strLog));
        }
    }

    public static void e(String strTag, String strLog)
    {
        if (mBErrorSwitch)
        {
            Log.e(strTag, buildMsg(strLog));
        }
    }

    /**
     * 设置调试开关
     * @param verbose
     * @param info
     * @param debug
     * @param warning
     * @param error
     */
    public static void setDebugSwitch(boolean verbose, boolean info, boolean debug, boolean warning, boolean error){
        mBVerboseSwitch = verbose;
        mBInfoSwitch = info;
        mBDebugSwithch = debug;
        mBWarningSwitch = warning;
        mBErrorSwitch = error;
    }

    public static void setPrintStackPath(int stackDepth){
        mPrintStackPath = stackDepth;
    }
}
