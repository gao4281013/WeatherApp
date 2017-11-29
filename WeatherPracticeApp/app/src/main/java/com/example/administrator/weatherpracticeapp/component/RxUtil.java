package com.example.administrator.weatherpracticeapp.component;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;
import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/29.
 * 封装RX的切换方法
 *
 */

public class RxUtil {

  public static <T>ObservableTransformer<T,T> io(){
    return schedulerTransfomer(Schedulers.io());
  }

  public static <T> ObservableTransformer<T, T> schedulerTransfomer(Scheduler scheduler) {
    return observable ->
        observable.subscribeOn(scheduler)
                  .observeOn(AndroidSchedulers.mainThread(),true);
  }

  public static <T> FlowableTransformer<T,T> iof(){
    return flowableTransfomer(Schedulers.io());
  }

  public static <T> FlowableTransformer<T, T> flowableTransfomer(Scheduler scheduler) {
    return flowable ->
        flowable.subscribeOn(scheduler)
            .observeOn(AndroidSchedulers.mainThread(),true);
  }

  public static <T> ObservableTransformer<T, T> acticiyLifeCycle(RxAppCompatActivity appCompatActivity) {
    return observable ->
        observable.compose(appCompatActivity.bindUntilEvent(ActivityEvent.DESTROY));
  }

  public static <T> ObservableTransformer<T, T> fragmentLifeCycle(RxFragment rxFragment) {
    return observable ->
        observable.compose(rxFragment.bindUntilEvent(FragmentEvent.DESTROY));
  }

  public static <T> FlowableTransformer<T, T> acticiyLifeCyclef(RxAppCompatActivity
      appCompatActivity) {
    return flowable ->
        flowable.compose(appCompatActivity.bindUntilEvent(ActivityEvent.DESTROY));
  }

  public static <T> FlowableTransformer<T, T> fragmentLifeCyclef(RxFragment rxFragment) {
    return flowable ->
        flowable.compose(rxFragment.bindUntilEvent(FragmentEvent.DESTROY));
  }
}
