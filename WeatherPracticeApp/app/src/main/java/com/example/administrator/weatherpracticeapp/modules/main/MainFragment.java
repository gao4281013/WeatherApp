package com.example.administrator.weatherpracticeapp.modules.main;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.example.administrator.weatherpracticeapp.component.RxBus;
import com.example.administrator.weatherpracticeapp.event.ChangeCityEvent;
import com.example.administrator.weatherpracticeapp.modules.main.adapter.WeatherAdapter;
import com.example.administrator.weatherpracticeapp.modules.main.domain.Weather;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
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
        .doOnNext(event ->{
          mSwipRefresh.setRefreshing(true);
          load();
        })
        .subscribe();
  }

  private void load() {

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
    new RxPermissions(getActivity())
        .request(Manifest.permission.ACCESS_COARSE_LOCATION)
        .doOnNext(o -> mSwipRefresh.setRefreshing(true))
        .doOnNext(granted ->{
          if (granted){
            location();
          }else {
            load();
          }
        })
        .subscribe();
  }

  private void location() {

  }

  private void initView() {

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
}
