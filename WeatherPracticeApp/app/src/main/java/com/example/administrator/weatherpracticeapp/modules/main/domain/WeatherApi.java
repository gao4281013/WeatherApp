package com.example.administrator.weatherpracticeapp.modules.main.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/11/20.
 */

public class WeatherApi implements Serializable{

  private List<Weather> HeWeather5;

  public List<Weather> getHeWeather5() {
    return HeWeather5;
  }

  public void setHeWeather5(List<Weather> HeWeather5) {
    this.HeWeather5 = HeWeather5;
  }
}
