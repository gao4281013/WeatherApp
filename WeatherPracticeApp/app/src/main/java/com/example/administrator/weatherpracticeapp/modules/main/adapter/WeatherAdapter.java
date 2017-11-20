package com.example.administrator.weatherpracticeapp.modules.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.example.administrator.weatherpracticeapp.component.AnimRecycleViewAdapter;

/**
 * Created by Administrator on 2017/11/20.
 */

public class WeatherAdapter  extends AnimRecycleViewAdapter<RecyclerView.ViewHolder>{

  private static final String TAG = WeatherAdapter.class.getSimpleName();

  private Context mContext;

  private static final int TYPE_ONE = 0;
  private static final int TYPE_TWO = 1;
  private static final int TYPE_THREE = 2;
  private static final int TYPE_FOUR = 3;

  @Override
  public int getItemViewType(int position) {
    if (position == TYPE_ONE){
      return TYPE_ONE;
    }else if (position == TYPE_TWO){
      return TYPE_TWO;
    }else if (position == TYPE_THREE){
      return TYPE_THREE;
    }else if (position == TYPE_FOUR){
      return TYPE_FOUR;
    }
    return super.getItemViewType(position);
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return super.onCreateViewHolder(parent, viewType);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    super.onBindViewHolder(holder, position);
  }

  @Override
  public int getItemCount() {
    return super.getItemCount();
  }

  @Override
  public void showItemAnim(View view, int position) {
    super.showItemAnim(view, position);
  }
}
