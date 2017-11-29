package com.example.administrator.weatherpracticeapp.component;

import com.example.administrator.weatherpracticeapp.BuildConfig;
import com.example.administrator.weatherpracticeapp.base.BaseApplication;
import com.litesuits.orm.LiteOrm;

/**
 * Created by Administrator on 2017/11/29.
 */

public class OrmLite {
  static LiteOrm sLiteOrm;

  public static LiteOrm getInstance(){
    getOrmHolder();
    return sLiteOrm;
  }

  private static OrmLite getOrmHolder() {
    return OrmHolder.sInstance;
  }

  private OrmLite() {
    if (sLiteOrm == null){
      sLiteOrm = LiteOrm.newCascadeInstance(BaseApplication.getAppContext(),C.ORM_NAME);
    }
   sLiteOrm.setDebugged(BuildConfig.DEBUG);
  }

  private static class OrmHolder{
    private static final OrmLite sInstance = new OrmLite();
  }
}
