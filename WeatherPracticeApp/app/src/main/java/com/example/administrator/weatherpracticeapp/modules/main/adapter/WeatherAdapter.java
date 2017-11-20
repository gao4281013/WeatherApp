package com.example.administrator.weatherpracticeapp.modules.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.administrator.weatherpracticeapp.R;
import com.example.administrator.weatherpracticeapp.base.BaseViewHolder;
import com.example.administrator.weatherpracticeapp.component.AnimRecycleViewAdapter;
import com.example.administrator.weatherpracticeapp.modules.main.domain.Weather;

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

  private Weather mWeather;

  public WeatherAdapter(Weather weather) {
    mWeather = weather;
  }

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
    switch (viewType){
      case TYPE_ONE:
      return new NowWeatherViewHolder(LayoutInflater.from(mContext).inflate(R.layout
          .item_temperature,parent,false));
      case TYPE_TWO:
      return new HoursWeatherViewHolder(LayoutInflater.from(mContext).inflate(R.layout
          .item_hour_info,parent,false));
      case TYPE_THREE:
      return new SuggestionViewHolder(LayoutInflater.from(mContext).inflate(R.layout
          .item_suggestion,parent,false));
      case TYPE_FOUR:
      return new ForcastViewHolder(LayoutInflater.from(mContext).inflate(R.layout
          .item_forcast_info,parent,false));
    }
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


  /**
   * 当前天气情况
   * */
  class NowWeatherViewHolder extends BaseViewHolder<Weather>{
    public NowWeatherViewHolder(View itemView) {
      super(itemView);
    }

    @Override
    protected void bind(Weather weather) {

    }
  }

  private class HoursWeatherViewHolder extends RecyclerView.ViewHolder {
    public HoursWeatherViewHolder(View inflate) {
      super(inflate);
    }
  }

  private class SuggestionViewHolder extends RecyclerView.ViewHolder {
    public SuggestionViewHolder(View inflate) {
      super(inflate);
    }
  }

  private class ForcastViewHolder extends RecyclerView.ViewHolder {
    public ForcastViewHolder(View inflate) {
      super(inflate);
    }
  }
}
