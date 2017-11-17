package com.example.administrator.weatherpracticeapp.base;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatDelegate;
import com.example.administrator.weatherpracticeapp.BuildConfig;
import com.facebook.stetho.Stetho;
import com.github.moduth.blockcanary.BlockCanary;
import com.hugo.watcher.Watcher;
import com.squareup.leakcanary.LeakCanary;
import im.fir.sdk.FIR;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by Administrator on 2017/11/17.
 */

public class BaseApplication extends Application {
  private static String sCacheDir;
  private static Context sAppContext;

  static {
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
  }

  @Override
  public void onCreate() {
    super.onCreate();
    sAppContext = getApplicationContext();

    if (!BuildConfig.DEBUG){
      FIR.init(sAppContext);
    }else {
      Watcher.getInstance().start(this);
      Stetho.initializeWithDefaults(this);
    }
    BlockCanary.install(this,new AppBlockCanaryContext()).start();
    LeakCanary.install(this);
    RxJavaPlugins.setErrorHandler(throwable -> {
      if (throwable != null){
        //PLog.e(throwable.toString());
      }else {
        //PLog.e("call onError but exception is null");
      }
    });

    if (getAppContext().getExternalCacheDir()!=null && isSdcardExisted()){
      sCacheDir = getApplicationContext().getExternalCacheDir().toString();
    }else {
      sCacheDir = getApplicationContext().getCacheDir().toString();
    }

  }
  private boolean isSdcardExisted(){
    return Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED);
  }

  public static String getAppCacheDir() {
    return sCacheDir;
  }

  public static Context getAppContext() {
    return sAppContext;
  }
}
