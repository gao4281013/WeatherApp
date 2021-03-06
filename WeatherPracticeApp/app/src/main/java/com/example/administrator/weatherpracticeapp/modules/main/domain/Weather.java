package com.example.administrator.weatherpracticeapp.modules.main.domain;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/11/20.
 */

public class Weather implements Serializable {

  /**
   * aqi : {"city":{"aqi":"57","co":"1","no2":"25","o3":"82","pm10":"63","pm25":"34","qlty":"良","so2":"12"}}
   * basic : {"city":"南京","cnty":"中国","id":"CN101190101","lat":"32.04154587","lon":"118.76741028","update":{"loc":"2017-11-20 15:52","utc":"2017-11-20 07:52"}}
   * daily_forecast : [{"astro":{"mr":"07:51","ms":"18:33","sr":"06:38","ss":"17:04"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2017-11-20","hum":"51","pcpn":"0.0","pop":"0","pres":"1029","tmp":{"max":"11","min":"3"},"uv":"4","vis":"10","wind":{"deg":"40","dir":"东北风","sc":"微风","spd":"11"}},{"astro":{"mr":"08:42","ms":"19:18","sr":"06:38","ss":"17:04"},"cond":{"code_d":"104","code_n":"104","txt_d":"阴","txt_n":"阴"},"date":"2017-11-21","hum":"62","pcpn":"0.0","pop":"0","pres":"1024","tmp":{"max":"13","min":"7"},"uv":"3","vis":"10","wind":{"deg":"155","dir":"东南风","sc":"微风","spd":"16"}},{"astro":{"mr":"09:32","ms":"20:05","sr":"06:39","ss":"17:04"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2017-11-22","hum":"49","pcpn":"0.0","pop":"0","pres":"1025","tmp":{"max":"15","min":"4"},"uv":"4","vis":"10","wind":{"deg":"329","dir":"西北风","sc":"3-4","spd":"17"}},{"astro":{"mr":"10:17","ms":"20:56","sr":"06:40","ss":"17:03"},"cond":{"code_d":"101","code_n":"100","txt_d":"多云","txt_n":"晴"},"date":"2017-11-23","hum":"33","pcpn":"0.0","pop":"0","pres":"1027","tmp":{"max":"12","min":"4"},"uv":"4","vis":"10","wind":{"deg":"274","dir":"西风","sc":"微风","spd":"10"}},{"astro":{"mr":"11:01","ms":"21:48","sr":"06:41","ss":"17:03"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2017-11-24","hum":"33","pcpn":"0.0","pop":"0","pres":"1026","tmp":{"max":"15","min":"3"},"uv":"4","vis":"10","wind":{"deg":"250","dir":"西南风","sc":"微风","spd":"12"}},{"astro":{"mr":"11:42","ms":"22:42","sr":"06:42","ss":"17:03"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2017-11-25","hum":"65","pcpn":"0.0","pop":"0","pres":"1022","tmp":{"max":"16","min":"5"},"uv":"3","vis":"9","wind":{"deg":"188","dir":"南风","sc":"微风","spd":"5"}},{"astro":{"mr":"12:19","ms":"23:38","sr":"06:43","ss":"17:03"},"cond":{"code_d":"101","code_n":"100","txt_d":"多云","txt_n":"晴"},"date":"2017-11-26","hum":"37","pcpn":"0.0","pop":"0","pres":"1027","tmp":{"max":"17","min":"7"},"uv":"3","vis":"10","wind":{"deg":"330","dir":"西北风","sc":"微风","spd":"15"}}]
   * hourly_forecast : [{"cond":{"code":"101","txt":"多云"},"date":"2017-11-20 16:00","hum":"46","pop":"0","pres":"1026","tmp":"9","wind":{"deg":"57","dir":"东北风","sc":"微风","spd":"6"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2017-11-20 19:00","hum":"59","pop":"0","pres":"1026","tmp":"8","wind":{"deg":"78","dir":"东北风","sc":"微风","spd":"8"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2017-11-20 22:00","hum":"65","pop":"0","pres":"1026","tmp":"5","wind":{"deg":"89","dir":"东风","sc":"微风","spd":"7"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2017-11-21 01:00","hum":"68","pop":"0","pres":"1027","tmp":"3","wind":{"deg":"76","dir":"东北风","sc":"微风","spd":"8"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2017-11-21 04:00","hum":"76","pop":"0","pres":"1026","tmp":"3","wind":{"deg":"88","dir":"东风","sc":"微风","spd":"8"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2017-11-21 07:00","hum":"87","pop":"0","pres":"1026","tmp":"3","wind":{"deg":"101","dir":"东风","sc":"微风","spd":"9"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2017-11-21 10:00","hum":"60","pop":"0","pres":"1026","tmp":"7","wind":{"deg":"120","dir":"东南风","sc":"微风","spd":"13"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2017-11-21 13:00","hum":"47","pop":"0","pres":"1022","tmp":"10","wind":{"deg":"132","dir":"东南风","sc":"微风","spd":"14"}}]
   * now : {"cond":{"code":"101","txt":"多云"},"fl":"10","hum":"51","pcpn":"0","pres":"1028","tmp":"9","vis":"10","wind":{"deg":"64","dir":"东北风","sc":"微风","spd":"7"}}
   * status : ok
   * suggestion : {"air":{"brf":"良","txt":"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"},"comf":{"brf":"较舒适","txt":"白天天气晴好，早晚会感觉偏凉，午后舒适、宜人。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drsg":{"brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},"flu":{"brf":"较易发","txt":"天凉，昼夜温差较大，较易发生感冒，请适当增减衣服，体质较弱的朋友请注意适当防护。"},"sport":{"brf":"较适宜","txt":"天气较好，但考虑风力较强且气温较低，推荐您进行室内运动，若在户外运动注意防风并适当增减衣物。"},"trav":{"brf":"适宜","txt":"天气较好，温度适宜，但风稍微有点大。这样的天气适宜旅游，您可以尽情地享受大自然的无限风光。"},"uv":{"brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}}
   */

  @SerializedName("aqi")
  public AqiBean aqi;
  @SerializedName("basic")
  public BasicBean basic;
  @SerializedName("now")
  public NowBean now;
  @SerializedName("status")
  public String status;
  @SerializedName("suggestion")
  public SuggestionBean suggestion;
  @SerializedName("daily_forecast")
  public List<DailyForecastBean> daily_forecast;
  @SerializedName("hourly_forecast")
  public List<HourlyForecastBean> hourly_forecast;


  public static class AqiBean {
    /**
     * city : {"aqi":"57","co":"1","no2":"25","o3":"82","pm10":"63","pm25":"34","qlty":"良","so2":"12"}
     */

    private CityBean city;

    public CityBean getCity() {
      return city;
    }

    public void setCity(CityBean city) {
      this.city = city;
    }

    public static class CityBean {
      /**
       * aqi : 57
       * co : 1
       * no2 : 25
       * o3 : 82
       * pm10 : 63
       * pm25 : 34
       * qlty : 良
       * so2 : 12
       */

      private String aqi;
      private String co;
      private String no2;
      private String o3;
      private String pm10;
      private String pm25;
      private String qlty;
      private String so2;

      public String getAqi() {
        return aqi;
      }

      public void setAqi(String aqi) {
        this.aqi = aqi;
      }

      public String getCo() {
        return co;
      }

      public void setCo(String co) {
        this.co = co;
      }

      public String getNo2() {
        return no2;
      }

      public void setNo2(String no2) {
        this.no2 = no2;
      }

      public String getO3() {
        return o3;
      }

      public void setO3(String o3) {
        this.o3 = o3;
      }

      public String getPm10() {
        return pm10;
      }

      public void setPm10(String pm10) {
        this.pm10 = pm10;
      }

      public String getPm25() {
        return pm25;
      }

      public void setPm25(String pm25) {
        this.pm25 = pm25;
      }

      public String getQlty() {
        return qlty;
      }

      public void setQlty(String qlty) {
        this.qlty = qlty;
      }

      public String getSo2() {
        return so2;
      }

      public void setSo2(String so2) {
        this.so2 = so2;
      }
    }
  }

  public static class BasicBean {
    /**
     * city : 南京
     * cnty : 中国
     * id : CN101190101
     * lat : 32.04154587
     * lon : 118.76741028
     * update : {"loc":"2017-11-20 15:52","utc":"2017-11-20 07:52"}
     */

    private String city;
    private String cnty;
    private String id;
    private String lat;
    private String lon;
    private UpdateBean update;

    public String getCity() {
      return city;
    }

    public void setCity(String city) {
      this.city = city;
    }

    public String getCnty() {
      return cnty;
    }

    public void setCnty(String cnty) {
      this.cnty = cnty;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getLat() {
      return lat;
    }

    public void setLat(String lat) {
      this.lat = lat;
    }

    public String getLon() {
      return lon;
    }

    public void setLon(String lon) {
      this.lon = lon;
    }

    public UpdateBean getUpdate() {
      return update;
    }

    public void setUpdate(UpdateBean update) {
      this.update = update;
    }

    public static class UpdateBean {
      /**
       * loc : 2017-11-20 15:52
       * utc : 2017-11-20 07:52
       */

      private String loc;
      private String utc;

      public String getLoc() {
        return loc;
      }

      public void setLoc(String loc) {
        this.loc = loc;
      }

      public String getUtc() {
        return utc;
      }

      public void setUtc(String utc) {
        this.utc = utc;
      }
    }
  }

  public static class NowBean {
    /**
     * cond : {"code":"101","txt":"多云"}
     * fl : 10
     * hum : 51
     * pcpn : 0
     * pres : 1028
     * tmp : 9
     * vis : 10
     * wind : {"deg":"64","dir":"东北风","sc":"微风","spd":"7"}
     */

    private CondBean cond;
    private String fl;
    private String hum;
    private String pcpn;
    private String pres;
    private String tmp;
    private String vis;
    private WindBean wind;

    public CondBean getCond() {
      return cond;
    }

    public void setCond(CondBean cond) {
      this.cond = cond;
    }

    public String getFl() {
      return fl;
    }

    public void setFl(String fl) {
      this.fl = fl;
    }

    public String getHum() {
      return hum;
    }

    public void setHum(String hum) {
      this.hum = hum;
    }

    public String getPcpn() {
      return pcpn;
    }

    public void setPcpn(String pcpn) {
      this.pcpn = pcpn;
    }

    public String getPres() {
      return pres;
    }

    public void setPres(String pres) {
      this.pres = pres;
    }

    public String getTmp() {
      return tmp;
    }

    public void setTmp(String tmp) {
      this.tmp = tmp;
    }

    public String getVis() {
      return vis;
    }

    public void setVis(String vis) {
      this.vis = vis;
    }

    public WindBean getWind() {
      return wind;
    }

    public void setWind(WindBean wind) {
      this.wind = wind;
    }

    public static class CondBean {
      /**
       * code : 101
       * txt : 多云
       */

      private String code;
      private String txt;

      public String getCode() {
        return code;
      }

      public void setCode(String code) {
        this.code = code;
      }

      public String getTxt() {
        return txt;
      }

      public void setTxt(String txt) {
        this.txt = txt;
      }
    }

    public static class WindBean {
      /**
       * deg : 64
       * dir : 东北风
       * sc : 微风
       * spd : 7
       */

      private String deg;
      private String dir;
      private String sc;
      private String spd;

      public String getDeg() {
        return deg;
      }

      public void setDeg(String deg) {
        this.deg = deg;
      }

      public String getDir() {
        return dir;
      }

      public void setDir(String dir) {
        this.dir = dir;
      }

      public String getSc() {
        return sc;
      }

      public void setSc(String sc) {
        this.sc = sc;
      }

      public String getSpd() {
        return spd;
      }

      public void setSpd(String spd) {
        this.spd = spd;
      }
    }
  }

  public static class SuggestionBean {
    /**
     * air : {"brf":"良","txt":"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"}
     * comf : {"brf":"较舒适","txt":"白天天气晴好，早晚会感觉偏凉，午后舒适、宜人。"}
     * cw : {"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
     * drsg : {"brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"}
     * flu : {"brf":"较易发","txt":"天凉，昼夜温差较大，较易发生感冒，请适当增减衣服，体质较弱的朋友请注意适当防护。"}
     * sport : {"brf":"较适宜","txt":"天气较好，但考虑风力较强且气温较低，推荐您进行室内运动，若在户外运动注意防风并适当增减衣物。"}
     * trav : {"brf":"适宜","txt":"天气较好，温度适宜，但风稍微有点大。这样的天气适宜旅游，您可以尽情地享受大自然的无限风光。"}
     * uv : {"brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}
     */

    private AirBean air;
    private ComfBean comf;
    private CwBean cw;
    private DrsgBean drsg;
    private FluBean flu;
    private SportBean sport;
    private TravBean trav;
    private UvBean uv;

    public AirBean getAir() {
      return air;
    }

    public void setAir(AirBean air) {
      this.air = air;
    }

    public ComfBean getComf() {
      return comf;
    }

    public void setComf(ComfBean comf) {
      this.comf = comf;
    }

    public CwBean getCw() {
      return cw;
    }

    public void setCw(CwBean cw) {
      this.cw = cw;
    }

    public DrsgBean getDrsg() {
      return drsg;
    }

    public void setDrsg(DrsgBean drsg) {
      this.drsg = drsg;
    }

    public FluBean getFlu() {
      return flu;
    }

    public void setFlu(FluBean flu) {
      this.flu = flu;
    }

    public SportBean getSport() {
      return sport;
    }

    public void setSport(SportBean sport) {
      this.sport = sport;
    }

    public TravBean getTrav() {
      return trav;
    }

    public void setTrav(TravBean trav) {
      this.trav = trav;
    }

    public UvBean getUv() {
      return uv;
    }

    public void setUv(UvBean uv) {
      this.uv = uv;
    }

    public static class AirBean {
      /**
       * brf : 良
       * txt : 气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。
       */

      private String brf;
      private String txt;

      public String getBrf() {
        return brf;
      }

      public void setBrf(String brf) {
        this.brf = brf;
      }

      public String getTxt() {
        return txt;
      }

      public void setTxt(String txt) {
        this.txt = txt;
      }
    }

    public static class ComfBean {
      /**
       * brf : 较舒适
       * txt : 白天天气晴好，早晚会感觉偏凉，午后舒适、宜人。
       */

      private String brf;
      private String txt;

      public String getBrf() {
        return brf;
      }

      public void setBrf(String brf) {
        this.brf = brf;
      }

      public String getTxt() {
        return txt;
      }

      public void setTxt(String txt) {
        this.txt = txt;
      }
    }

    public static class CwBean {
      /**
       * brf : 较适宜
       * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
       */

      private String brf;
      private String txt;

      public String getBrf() {
        return brf;
      }

      public void setBrf(String brf) {
        this.brf = brf;
      }

      public String getTxt() {
        return txt;
      }

      public void setTxt(String txt) {
        this.txt = txt;
      }
    }

    public static class DrsgBean {
      /**
       * brf : 较冷
       * txt : 建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。
       */

      private String brf;
      private String txt;

      public String getBrf() {
        return brf;
      }

      public void setBrf(String brf) {
        this.brf = brf;
      }

      public String getTxt() {
        return txt;
      }

      public void setTxt(String txt) {
        this.txt = txt;
      }
    }

    public static class FluBean {
      /**
       * brf : 较易发
       * txt : 天凉，昼夜温差较大，较易发生感冒，请适当增减衣服，体质较弱的朋友请注意适当防护。
       */

      private String brf;
      private String txt;

      public String getBrf() {
        return brf;
      }

      public void setBrf(String brf) {
        this.brf = brf;
      }

      public String getTxt() {
        return txt;
      }

      public void setTxt(String txt) {
        this.txt = txt;
      }
    }

    public static class SportBean {
      /**
       * brf : 较适宜
       * txt : 天气较好，但考虑风力较强且气温较低，推荐您进行室内运动，若在户外运动注意防风并适当增减衣物。
       */

      private String brf;
      private String txt;

      public String getBrf() {
        return brf;
      }

      public void setBrf(String brf) {
        this.brf = brf;
      }

      public String getTxt() {
        return txt;
      }

      public void setTxt(String txt) {
        this.txt = txt;
      }
    }

    public static class TravBean {
      /**
       * brf : 适宜
       * txt : 天气较好，温度适宜，但风稍微有点大。这样的天气适宜旅游，您可以尽情地享受大自然的无限风光。
       */

      private String brf;
      private String txt;

      public String getBrf() {
        return brf;
      }

      public void setBrf(String brf) {
        this.brf = brf;
      }

      public String getTxt() {
        return txt;
      }

      public void setTxt(String txt) {
        this.txt = txt;
      }
    }

    public static class UvBean {
      /**
       * brf : 最弱
       * txt : 属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。
       */

      private String brf;
      private String txt;

      public String getBrf() {
        return brf;
      }

      public void setBrf(String brf) {
        this.brf = brf;
      }

      public String getTxt() {
        return txt;
      }

      public void setTxt(String txt) {
        this.txt = txt;
      }
    }
  }

  public static class DailyForecastBean {
    /**
     * astro : {"mr":"07:51","ms":"18:33","sr":"06:38","ss":"17:04"}
     * cond : {"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"}
     * date : 2017-11-20
     * hum : 51
     * pcpn : 0.0
     * pop : 0
     * pres : 1029
     * tmp : {"max":"11","min":"3"}
     * uv : 4
     * vis : 10
     * wind : {"deg":"40","dir":"东北风","sc":"微风","spd":"11"}
     */

    private AstroBean astro;
    private CondBeanX cond;
    private String date;
    private String hum;
    private String pcpn;
    private String pop;
    private String pres;
    private TmpBean tmp;
    private String uv;
    private String vis;
    private WindBeanX wind;

    public AstroBean getAstro() {
      return astro;
    }

    public void setAstro(AstroBean astro) {
      this.astro = astro;
    }

    public CondBeanX getCond() {
      return cond;
    }

    public void setCond(CondBeanX cond) {
      this.cond = cond;
    }

    public String getDate() {
      return date;
    }

    public void setDate(String date) {
      this.date = date;
    }

    public String getHum() {
      return hum;
    }

    public void setHum(String hum) {
      this.hum = hum;
    }

    public String getPcpn() {
      return pcpn;
    }

    public void setPcpn(String pcpn) {
      this.pcpn = pcpn;
    }

    public String getPop() {
      return pop;
    }

    public void setPop(String pop) {
      this.pop = pop;
    }

    public String getPres() {
      return pres;
    }

    public void setPres(String pres) {
      this.pres = pres;
    }

    public TmpBean getTmp() {
      return tmp;
    }

    public void setTmp(TmpBean tmp) {
      this.tmp = tmp;
    }

    public String getUv() {
      return uv;
    }

    public void setUv(String uv) {
      this.uv = uv;
    }

    public String getVis() {
      return vis;
    }

    public void setVis(String vis) {
      this.vis = vis;
    }

    public WindBeanX getWind() {
      return wind;
    }

    public void setWind(WindBeanX wind) {
      this.wind = wind;
    }

    public static class AstroBean {
      /**
       * mr : 07:51
       * ms : 18:33
       * sr : 06:38
       * ss : 17:04
       */

      private String mr;
      private String ms;
      private String sr;
      private String ss;

      public String getMr() {
        return mr;
      }

      public void setMr(String mr) {
        this.mr = mr;
      }

      public String getMs() {
        return ms;
      }

      public void setMs(String ms) {
        this.ms = ms;
      }

      public String getSr() {
        return sr;
      }

      public void setSr(String sr) {
        this.sr = sr;
      }

      public String getSs() {
        return ss;
      }

      public void setSs(String ss) {
        this.ss = ss;
      }
    }

    public static class CondBeanX {
      /**
       * code_d : 101
       * code_n : 101
       * txt_d : 多云
       * txt_n : 多云
       */

      private String code_d;
      private String code_n;
      private String txt_d;
      private String txt_n;

      public String getCode_d() {
        return code_d;
      }

      public void setCode_d(String code_d) {
        this.code_d = code_d;
      }

      public String getCode_n() {
        return code_n;
      }

      public void setCode_n(String code_n) {
        this.code_n = code_n;
      }

      public String getTxt_d() {
        return txt_d;
      }

      public void setTxt_d(String txt_d) {
        this.txt_d = txt_d;
      }

      public String getTxt_n() {
        return txt_n;
      }

      public void setTxt_n(String txt_n) {
        this.txt_n = txt_n;
      }
    }

    public static class TmpBean {
      /**
       * max : 11
       * min : 3
       */

      private String max;
      private String min;

      public String getMax() {
        return max;
      }

      public void setMax(String max) {
        this.max = max;
      }

      public String getMin() {
        return min;
      }

      public void setMin(String min) {
        this.min = min;
      }
    }

    public static class WindBeanX {
      /**
       * deg : 40
       * dir : 东北风
       * sc : 微风
       * spd : 11
       */

      private String deg;
      private String dir;
      private String sc;
      private String spd;

      public String getDeg() {
        return deg;
      }

      public void setDeg(String deg) {
        this.deg = deg;
      }

      public String getDir() {
        return dir;
      }

      public void setDir(String dir) {
        this.dir = dir;
      }

      public String getSc() {
        return sc;
      }

      public void setSc(String sc) {
        this.sc = sc;
      }

      public String getSpd() {
        return spd;
      }

      public void setSpd(String spd) {
        this.spd = spd;
      }
    }
  }

  public static class HourlyForecastBean {
    /**
     * cond : {"code":"101","txt":"多云"}
     * date : 2017-11-20 16:00
     * hum : 46
     * pop : 0
     * pres : 1026
     * tmp : 9
     * wind : {"deg":"57","dir":"东北风","sc":"微风","spd":"6"}
     */

    private CondBeanXX cond;
    private String date;
    private String hum;
    private String pop;
    private String pres;
    private String tmp;
    private WindBeanXX wind;

    public CondBeanXX getCond() {
      return cond;
    }

    public void setCond(CondBeanXX cond) {
      this.cond = cond;
    }

    public String getDate() {
      return date;
    }

    public void setDate(String date) {
      this.date = date;
    }

    public String getHum() {
      return hum;
    }

    public void setHum(String hum) {
      this.hum = hum;
    }

    public String getPop() {
      return pop;
    }

    public void setPop(String pop) {
      this.pop = pop;
    }

    public String getPres() {
      return pres;
    }

    public void setPres(String pres) {
      this.pres = pres;
    }

    public String getTmp() {
      return tmp;
    }

    public void setTmp(String tmp) {
      this.tmp = tmp;
    }

    public WindBeanXX getWind() {
      return wind;
    }

    public void setWind(WindBeanXX wind) {
      this.wind = wind;
    }

    public static class CondBeanXX {
      /**
       * code : 101
       * txt : 多云
       */

      private String code;
      private String txt;

      public String getCode() {
        return code;
      }

      public void setCode(String code) {
        this.code = code;
      }

      public String getTxt() {
        return txt;
      }

      public void setTxt(String txt) {
        this.txt = txt;
      }
    }

    public static class WindBeanXX {
      /**
       * deg : 57
       * dir : 东北风
       * sc : 微风
       * spd : 6
       */

      private String deg;
      private String dir;
      private String sc;
      private String spd;

      public String getDeg() {
        return deg;
      }

      public void setDeg(String deg) {
        this.deg = deg;
      }

      public String getDir() {
        return dir;
      }

      public void setDir(String dir) {
        this.dir = dir;
      }

      public String getSc() {
        return sc;
      }

      public void setSc(String sc) {
        this.sc = sc;
      }

      public String getSpd() {
        return spd;
      }

      public void setSpd(String spd) {
        this.spd = spd;
      }
    }
  }
}
