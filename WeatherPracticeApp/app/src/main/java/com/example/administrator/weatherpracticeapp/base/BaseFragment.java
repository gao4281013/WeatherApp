package com.example.administrator.weatherpracticeapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.administrator.weatherpracticeapp.R;
import com.trello.rxlifecycle2.components.support.RxFragment;

public abstract class BaseFragment extends RxFragment {

  protected boolean mIsCreateView = false;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (isVisibleToUser && mIsCreateView){
      lazyLoad();
    }
  }

  /**
   * 加载数据操作，在试图创建之前初始化
   * */
  protected abstract void lazyLoad();

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_base, container, false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (getUserVisibleHint()){
      lazyLoad();
    }
  }

  protected void safeSetTitle(String title){
    android.support.v7.app.ActionBar appBarLayout = ((AppCompatActivity)getActivity()).getSupportActionBar();
    if (appBarLayout != null){
      appBarLayout.setTitle(title);
    }
  }
}
