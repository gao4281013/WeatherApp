package com.example.administrator.weatherpracticeapp;

import android.os.Bundle;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;


//┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃ 　
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//┃　　　┃   神兽保佑　　　　　　　　
//┃　　　┃   代码无BUG！
//┃　　　┗━━━┓
//┃　　　　　　　┣┓
//┃　　　　　　　┏┛
//┗┓┓┏━┳┓┏┛
//  ┃┫┫　┃┫┫
//  ┗┻┛　┗┻┛
public class SplashActivity extends RxAppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    if (savedInstanceState == null){
        getSupportFragmentManager().beginTransaction().replace(R.id.splash_layout,new SplashFragment()).commit();
    }
  }
}
