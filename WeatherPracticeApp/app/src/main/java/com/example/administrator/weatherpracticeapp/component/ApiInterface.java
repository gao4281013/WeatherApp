package com.example.administrator.weatherpracticeapp.component;

import com.example.administrator.weatherpracticeapp.modules.about.Version;
import com.example.administrator.weatherpracticeapp.modules.main.domain.WeatherApi;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/11/29.
 */

public interface ApiInterface {

  String host = "https://free-api.heweather.com/v5/";

  @GET("weather")
  Observable<WeatherApi> mWeatherApi(@Query("city") String city,@Query("key") String key);

  @GET("weather")
  Flowable<WeatherApi> mWeatherApiF(@Query("city") String city,@Query("key") String key);

  @GET("http://api.fir.im/apps/latest/5630e5f1f2fc425c52000006")
  Observable<Version> mVersionApi(@Query("api_token")String api_token);

}
