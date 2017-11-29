package com.example.administrator.weatherpracticeapp.modules.about;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/11/29.
 */

public class Version {

  /**
   * name : 就看天气
   * version : 34
   * changelog : - [新增] 天气接口升级
   - - -
   开始持续更新...
   * updated_at : 1494244628
   * versionShort : 2.3.1
   * build : 34
   * installUrl : http://download.fir.im/v2/app/install/5630e5f1f2fc425c52000006?download_token=4fc087ec1c755290385ee59f71b3937a&source=update
   * install_url : http://download.fir.im/v2/app/install/5630e5f1f2fc425c52000006?download_token=4fc087ec1c755290385ee59f71b3937a&source=update
   * direct_install_url : http://download.fir.im/v2/app/install/5630e5f1f2fc425c52000006?download_token=4fc087ec1c755290385ee59f71b3937a&source=update
   * update_url : http://fir.im/seeWeather
   * binary : {"fsize":4380886}
   */

  @SerializedName("name") public String name;
  @SerializedName("version") public String version;
  @SerializedName("changelog") public String changelog;
  @SerializedName("updated_at") public int updatedAt;
  @SerializedName("versionShort") public String versionShort;
  @SerializedName("build") public String build;
  @SerializedName("install_url") public String installUrl;
  @SerializedName("direct_install_url") public String directInstallUrl;
  @SerializedName("update_url") public String updateUrl;
  @SerializedName("binary") public BinaryBean binary;

  public static class BinaryBean {
    /**
     * fsize : 4380886
     */

    @SerializedName("fsize") public int fsize;
  }
}
