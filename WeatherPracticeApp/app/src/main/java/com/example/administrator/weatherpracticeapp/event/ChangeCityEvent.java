package com.example.administrator.weatherpracticeapp.event;

/**
 * Created by Administrator on 2017/11/21.
 */

public class ChangeCityEvent {
  private String city;
  private boolean isSetting;

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public boolean isSetting() {
    return isSetting;
  }

  public void setSetting(boolean setting) {
    isSetting = setting;
  }
}
