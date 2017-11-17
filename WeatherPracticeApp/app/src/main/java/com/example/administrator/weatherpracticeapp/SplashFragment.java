package com.example.administrator.weatherpracticeapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SplashFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SplashFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SplashFragment extends RxFragment {
  @BindView(R.id.splasg_img) ImageView mSplasgImg;
  @BindView(R.id.jump_btn) Button mJumpBtn;
  private final int AllCount = 3000;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_splash, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initView();
  }

  private void initView() {
    String url = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=224572817,1826757662&fm=27&gp=0.jpg";
    //R.mipmap.guide_page_04
    Glide.with(getActivity()).load(url).centerCrop().into(mSplasgImg);
    mJumpBtn.setVisibility(View.VISIBLE);
    MyCountDownTimer myCountDownTimer = new MyCountDownTimer(5000,1000);
    myCountDownTimer.start();
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }


  /**
   * 继承 CountDownTimer 防范
   *
   * 重写 父类的方法 onTick() 、 onFinish()
   */

  class MyCountDownTimer extends CountDownTimer {
    /**
     *
     * @param millisInFuture
     *            表示以毫秒为单位 倒计时的总数
     *
     *            例如 millisInFuture=1000 表示1秒
     *
     * @param countDownInterval
     *            表示 间隔 多少微秒 调用一次 onTick 方法
     *
     *            例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
     *
     */
    public MyCountDownTimer(long millisInFuture, long countDownInterval) {
      super(millisInFuture, countDownInterval);
    }

    @Override
    public void onFinish() {
      startActivity(new Intent(getActivity(),MainActivity.class));
    }

    @Override
    public void onTick(long millisUntilFinished) {
      Log.i("MainActivity", millisUntilFinished + "");
      mJumpBtn.setText("倒计时(" + millisUntilFinished / 1000 + ")...");
    }
  }
}
