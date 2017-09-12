package momo.cn.edu.fjnu.androidutils.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 崩溃处理器
 * Created by GaoFei on 2016/1/3.
 */
public  abstract class BaseCrashHandler implements Thread.UncaughtExceptionHandler{


    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        StringWriter writer = new StringWriter();
        ex.printStackTrace(new PrintWriter(writer));
        handleException(ex.toString());
    }

    /**
     * 子类重写这个方法，用于处理崩溃异常
     * @param errorMsg
     */
    public abstract void handleException(String errorMsg);

}
