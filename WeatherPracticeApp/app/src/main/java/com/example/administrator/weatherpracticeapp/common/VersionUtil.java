package com.example.administrator.weatherpracticeapp.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Administrator on 2017/11/21.
 */

public class VersionUtil {

  public static String getVersion(Context context){
    try {
      PackageManager manager = context.getPackageManager();
      PackageInfo info = manager.getPackageInfo(context.getPackageName(),0);
      return info.versionName;
    }catch (Exception e){
      e.printStackTrace();
      return "找不到版本号";
    }
  }

  public static void CheckVersion(Context context){
  }
}
