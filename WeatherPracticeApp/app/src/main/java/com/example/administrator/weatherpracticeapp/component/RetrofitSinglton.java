package com.example.administrator.weatherpracticeapp.component;

import android.util.Log;
import com.example.administrator.weatherpracticeapp.BuildConfig;
import com.example.administrator.weatherpracticeapp.base.BaseApplication;
import com.example.administrator.weatherpracticeapp.common.ToastUtil;
import com.example.administrator.weatherpracticeapp.common.Util;
import com.example.administrator.weatherpracticeapp.modules.about.Version;
import com.example.administrator.weatherpracticeapp.modules.main.domain.CityORM;
import com.example.administrator.weatherpracticeapp.modules.main.domain.Weather;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.litesuits.orm.db.assit.WhereBuilder;
import io.reactivex.Observable;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/11/17.
 */

public class RetrofitSinglton {

  private static ApiInterface sApiInterface = null;
  private static Retrofit sRetrofit = null;
  private static OkHttpClient sOkHttpClient = null;

  private void init() {
    initOkHttp();
    initRetrofit();
    sApiInterface = sRetrofit.create(ApiInterface.class);
  }

  public RetrofitSinglton() {
    init();
  }

  public static RetrofitSinglton getInstance() {
    return SingletonHolder.INSTANCE;
  }

  private static class SingletonHolder {
    private static final RetrofitSinglton INSTANCE = new RetrofitSinglton();
  }

  private void initRetrofit() {
    sRetrofit = new Retrofit.Builder()
        .baseUrl(ApiInterface.host)
        .client(sOkHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  private void initOkHttp() {

    /**
     * okhttp封装拦截，进行缓存
     * */
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    File fileCache = new File(C.NET_CACHE);
    Cache cache = new Cache(fileCache, 1024 * 1024 * 50);
    Interceptor cacheIntercepter = chain -> {
      Request request = chain.request();
      if (Util.isNetworkConnected(BaseApplication.getAppContext())) {
        request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
      }
      Response response = chain.proceed(request);
      Response.Builder newBuilder = response.newBuilder();
      if (Util.isNetworkConnected(BaseApplication.getAppContext())) {
        //有网是不缓存
        int maxAge = 0;
        newBuilder.addHeader("Cache-Control", "public,max-age=" + maxAge);
      } else {
        //无网络是缓存4周
        int maxStale = 60 * 60 * 24 * 7 * 4;
        newBuilder.addHeader("Cache-Control", "public,only-if-cached,max-stale=" + maxStale);
      }
      return newBuilder.build();
    };

    builder.cache(cache).addInterceptor(cacheIntercepter);

    //日志显示级别
    HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
    //新建log拦截器
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
      @Override
      public void log(String message) {
        Log.d("GavinBody","OkHttp====Message:"+message);
      }
    });
    loggingInterceptor.setLevel(level);
    if (BuildConfig.DEBUG){
      builder.addNetworkInterceptor(new StethoInterceptor());
      builder.addInterceptor(loggingInterceptor);
    }

    //设置超时
    builder.connectTimeout(15, TimeUnit.SECONDS);
    builder.readTimeout(20,TimeUnit.SECONDS);
    builder.writeTimeout(20,TimeUnit.SECONDS);

    //错误重连
    builder.retryOnConnectionFailure(true);
    sOkHttpClient = builder.build();
  }


  private static Consumer<Throwable> disposeFailureInfo(Throwable t){
    return throwable -> {
      if (t.toString().contains("GaiException")  || t.toString().contains("SocketTimeoutException")) {
        t.toString().contains("UnknownHostException");
        ToastUtil.showShort("网络问题");
      }else if (t.toString().contains("API没有")){
        OrmLite.getInstance()
            .delete(new WhereBuilder(CityORM.class).where("name=?",Util.replaceInfo(t.getMessage
                ())));
        ToastUtil.showShort("错误"+t.getMessage());
      }
      GLog.e(RetrofitSinglton.class.getSimpleName(),t.getMessage());
    };
  }

  public Observable<Weather> fetchWeather(String city){
    return sApiInterface.mWeatherApi(city,C.KEY)
        .flatMap(weatherApi -> {
          String status = weatherApi.getHeWeather5().get(0).status;
          if ("no more requests".equals(status)){
            return Observable.error(new RuntimeException("API免费次数已用完"));
          }else if ("unknown city".equals(status)) {
            return Observable.error(new RuntimeException(String.format("API没有%s", city)));
          }
          return Observable.just(weatherApi);
        })
        .map(weatherApi -> weatherApi.getHeWeather5().get(0))
        .doOnError(RetrofitSinglton::disposeFailureInfo)
        .compose(RxUtil.io());
  }

  public Observable<Version> fetchVersion(){
      return sApiInterface.mVersionApi(C.API_TOKEN)
          .doOnError(RetrofitSinglton::disposeFailureInfo)
          .compose(RxUtil.io());
  }
}
