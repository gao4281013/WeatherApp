package com.example.administrator.weatherpracticeapp.component;

import android.util.Log;
import com.example.administrator.weatherpracticeapp.BuildConfig;
import com.example.administrator.weatherpracticeapp.base.BaseApplication;
import java.io.File;

/**
 * Created by Administrator on 2017/11/17.
 * 讲日子信息打印到logcat和日志文件中
 */

public class GLog {
  private static boolean isDebug = BuildConfig.DEBUG;
  private static final String PATH = BaseApplication.getAppCacheDir();
  private static final String GLOG_FILE_NAME = "GLOG.txt";

  /**
   * 是否将日志写入文件
   * */
  public static final boolean GLOG_WRITE_TO_FILE = true;

  /**
   * 错误信息
   * */
  public static void e(String TAG,String msg){
    Log.e(TAG,log(msg));
    if (GLOG_WRITE_TO_FILE){
      writeLogtoFile("e",TAG,msg);
    }
  }

  private static void writeLogtoFile(String e, String tag, String msg) {
      isExist(PATH);
      String needWriteMessage = "\r\n";


  }

  public static void isExist(String path){
    File file = new File(path);
    if (!file.exists()){
      try {
        file.mkdirs();

      }catch (Exception e){
        GLog.e(e.getMessage());
      }
    }
  }

  public static void e(String msg){
    e(getClassName(),msg);
  }

  private static String getClassName() {
    String result;
    StackTraceElement thisMethodStack = Thread.currentThread().getStackTrace()[2];
    result = thisMethodStack.getClassName();
    int lastIndex = result.lastIndexOf(".");
    result = result.substring(lastIndex);
    int i = result.indexOf("$");
    return i==-1?result:result.substring(0,i);
  }

  private static String log(String msg) {
    StackTraceElement[] stackTraces = Thread.currentThread().getStackTrace();
    StackTraceElement stackTraceElement = stackTraces[5];
    String className = stackTraceElement.getClassName();
    int lineNumber = stackTraceElement.getLineNumber();
    if (lineNumber<0) lineNumber = 0;
    return "("+className+":"+lineNumber+") "+ msg;
  }
}
