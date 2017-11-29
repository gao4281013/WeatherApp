package com.example.administrator.weatherpracticeapp.component;

import com.example.administrator.weatherpracticeapp.BuildConfig;
import com.example.administrator.weatherpracticeapp.base.BaseApplication;
import java.io.File;

/**
 * Created by Administrator on 2017/11/29.
 */

public class C {
  public static final String NET_CACHE = BaseApplication.getAppCacheDir()+ File.separator+"net_cache";

  public static final String ORM_NAME = "cities.db";
  public static final String KEY = BuildConfig.WeatherKey;
  public static final String API_TOKEN = BuildConfig.FirToken;
  public static final String MY_HOME = "西安";

}
