package com.example.administrator.weatherpracticeapp.modules.main;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.example.administrator.weatherpracticeapp.R;
import com.example.administrator.weatherpracticeapp.base.BaseFragment;
import com.example.administrator.weatherpracticeapp.common.SharedPreferenceUtil;
import com.example.administrator.weatherpracticeapp.common.ToastUtil;
import com.example.administrator.weatherpracticeapp.common.Util;
import com.example.administrator.weatherpracticeapp.common.VersionUtil;
import com.example.administrator.weatherpracticeapp.component.C;
import com.example.administrator.weatherpracticeapp.component.RetrofitSinglton;
import com.example.administrator.weatherpracticeapp.component.RxBus;
import com.example.administrator.weatherpracticeapp.component.RxUtil;
import com.example.administrator.weatherpracticeapp.event.ChangeCityEvent;
import com.example.administrator.weatherpracticeapp.modules.main.adapter.WeatherAdapter;
import com.example.administrator.weatherpracticeapp.modules.main.domain.Weather;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class MainFragment extends BaseFragment {

  @BindView(R.id.recycle_view) RecyclerView mRecycleView;
  @BindView(R.id.swipRefresh) SwipeRefreshLayout mSwipRefresh;
  @BindView(R.id.progressBar) ProgressBar mProgressBar;
  @BindView(R.id.iv_erro) ImageView mIvErro;
  Unbinder unbinder;
  private View mView;

  private static Weather mWeather = new Weather();
  private WeatherAdapter mWeatherAdapter;

  private AMapLocationClient mAMapLocationClient;
  private AMapLocationClientOption mAMapLocationClientOption;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    RxBus.getDefault()
        .toObservable(ChangeCityEvent.class)
        .observeOn(AndroidSchedulers.mainThread())
        .filter(event -> isVisible())
        .doOnNext(event -> {
          mSwipRefresh.setRefreshing(true);
          load();
        })
        .subscribe();
  }

  private void load() {
     fetchDataByNetWork()
         .doOnSubscribe(aLong -> mSwipRefresh.setRefreshing(true))
         .doOnError(throwable -> {
           mIvErro.setVisibility(View.VISIBLE);
           mRecycleView.setVisibility(View.GONE);
           SharedPreferenceUtil.getInstance().setCityName(C.MY_HOME);
           safeSetTitle("找不到城市");
         })
         .doOnNext(weather -> {
           mIvErro.setVisibility(View.GONE);
           mRecycleView.setVisibility(View.VISIBLE);

           mWeather.status = weather.status;
           mWeather.aqi = weather.aqi;
           mWeather.basic = weather.basic;
           mWeather.suggestion = weather.suggestion;
           mWeather.now = weather.now;
           mWeather.daily_forecast = weather.daily_forecast;
           mWeather.hourly_forecast = weather.hourly_forecast;
           safeSetTitle(weather.basic.getCity());
           mWeatherAdapter.notifyDataSetChanged();
         })
         .doOnComplete(() ->{
           mSwipRefresh.setRefreshing(false);
           mProgressBar.setVisibility(View.GONE);
           ToastUtil.showShort("加载完毕");
         })
         .subscribe();


  }

  /**
   * 从网络获取
   * */
  private Observable<Weather> fetchDataByNetWork() {
    String cityName = SharedPreferenceUtil.getInstance().getCityName();
    return RetrofitSinglton.getInstance().
        fetchWeather(cityName)
        .compose(RxUtil.io());
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    if (mView == null) {
      mView = inflater.inflate(R.layout.fragment_main, container, false);
      ButterKnife.bind(this, mView);
    }
    mIsCreateView = true;
    unbinder = ButterKnife.bind(this, mView);
    return mView;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initView();
    new RxPermissions(getActivity()).request(Manifest.permission.ACCESS_COARSE_LOCATION)
        .doOnNext(o -> mSwipRefresh.setRefreshing(true))
        .doOnNext(granted -> {
          if (granted) {
            location();
          } else {
            load();
          }
          VersionUtil.checkVersion(getActivity());
        })
        .subscribe();
  }

  private void location() {
     //初始化定位
    mAMapLocationClient = new AMapLocationClient(getActivity());
    mAMapLocationClientOption = new AMapLocationClientOption();
    mAMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode
        .Battery_Saving);
    mAMapLocationClientOption.setNeedAddress(true);
    mAMapLocationClientOption.setOnceLocation(true);
    mAMapLocationClientOption.setWifiActiveScan(false);
    //设置定位间隔，单位毫秒

    int autoUpdateTime = SharedPreferenceUtil.getInstance().getAutoUpdate();
    mAMapLocationClientOption.setInterval(autoUpdateTime);
    mAMapLocationClient.setLocationOption(mAMapLocationClientOption);
    mAMapLocationClient.setLocationListener(aMapLocation -> {
      if (aMapLocation != null){
        if (aMapLocation.getErrorCode() ==0){
          aMapLocation.getLocationType();
          SharedPreferenceUtil.getInstance().setCityName(Util.replaceCity(aMapLocation.getCity()));
        }else {
          if (isAdded()){
            ToastUtil.showShort("定位失败，加载默认城市");
          }
        }
        load();
      }
    });
    mAMapLocationClient.startLocation();
  }

  private void initView() {
    if (mSwipRefresh !=null){
      mSwipRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
          android.R.color.holo_green_light,
          android.R.color.holo_orange_light,
          android.R.color.holo_red_light);
      mSwipRefresh.setOnRefreshListener( ()-> mSwipRefresh.postDelayed(this::load,1000) );
    }
    mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mWeatherAdapter = new WeatherAdapter(mWeather);
    mRecycleView.setAdapter(mWeatherAdapter);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  @Override
  protected void lazyLoad() {

  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    mAMapLocationClient.onDestroy();
  }
}
