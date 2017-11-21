package com.example.administrator.weatherpracticeapp.modules.main.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.example.administrator.weatherpracticeapp.R;
import com.example.administrator.weatherpracticeapp.base.BaseViewHolder;
import com.example.administrator.weatherpracticeapp.common.ImageLoader;
import com.example.administrator.weatherpracticeapp.common.SharedPreferenceUtil;
import com.example.administrator.weatherpracticeapp.common.Util;
import com.example.administrator.weatherpracticeapp.component.AnimRecycleViewAdapter;
import com.example.administrator.weatherpracticeapp.component.GLog;
import com.example.administrator.weatherpracticeapp.component.TimeUitl;
import com.example.administrator.weatherpracticeapp.modules.main.domain.Weather;

/**
 * Created by Administrator on 2017/11/20.
 */

public class WeatherAdapter extends AnimRecycleViewAdapter<RecyclerView.ViewHolder> {

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
    if (position == TYPE_ONE) {
      return TYPE_ONE;
    } else if (position == TYPE_TWO) {
      return TYPE_TWO;
    } else if (position == TYPE_THREE) {
      return TYPE_THREE;
    } else if (position == TYPE_FOUR) {
      return TYPE_FOUR;
    }
    return super.getItemViewType(position);
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    switch (viewType) {
      case TYPE_ONE:
        return new NowWeatherViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.item_temperature, parent, false));
      case TYPE_TWO:
        return new HoursWeatherViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.item_hour_info, parent, false));
      case TYPE_THREE:
        return new SuggestionViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.item_suggestion, parent, false));
      case TYPE_FOUR:
        return new ForcastViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.item_forcast_info, parent, false));
    }
    return super.onCreateViewHolder(parent, viewType);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    super.onBindViewHolder(holder, position);
    int type = getItemViewType(position);
    switch (type) {
      case TYPE_ONE:
        ((NowWeatherViewHolder) holder).bind(mWeather);
        break;
      case TYPE_TWO:
        ((HoursWeatherViewHolder) holder).bind(mWeather);
        break;
      case TYPE_THREE:
        ((SuggestionViewHolder) holder).bind(mWeather);

        break;
      case TYPE_FOUR:
        ((ForcastViewHolder) holder).bind(mWeather);
        break;
    }

    if (SharedPreferenceUtil.getInstance().getMainAnim()) {
      showItemAnim(holder.itemView, position);
    }
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
   */
  class NowWeatherViewHolder extends BaseViewHolder<Weather> {
    @BindView(R.id.temp_flu) TextView mTempFlu;
    @BindView(R.id.temp_max) TextView mTempMax;
    @BindView(R.id.temp_min) TextView mTempMin;
    @BindView(R.id.temp_layout) LinearLayout mTempLayout;
    @BindView(R.id.linear_temp) LinearLayout mLinearTemp;
    @BindView(R.id.temp_pm) TextView mTempPm;
    @BindView(R.id.temp_quality) TextView mTempQuality;
    @BindView(R.id.cardView) CardView mCardView;
    @BindView(R.id.weather_icon) ImageView mWeatherIcon;

    public NowWeatherViewHolder(View itemView) {
      super(itemView);
    }

    @Override
    protected void bind(Weather weather) {
      try {
        mTempFlu.setText(String.format("%s℃", weather.getNow().getTmp()));
        mTempMax.setText(
            String.format("↑ %s ℃", weather.getDaily_forecast().get(0).getTmp().getMax()));
        mTempMin.setText(
            String.format("↓ %s ℃", weather.getDaily_forecast().get(0).getTmp().getMin()));
        mTempPm.setText(
            String.format("PM2.5:%s ug/m³", Util.safeText(weather.getAqi().getCity().getPm25())));
        mTempQuality.setText(
            String.format("空气质量：%s", Util.safeText(weather.getAqi().getCity().getQlty())));
        ImageLoader.load(itemView.getContext(), SharedPreferenceUtil.getInstance()
            .getInt(weather.getNow().getCond().getTxt(), R.mipmap.none), mWeatherIcon);
      } catch (Exception e) {
        GLog.e(TAG, e.getMessage());
      }
    }
  }

  private class HoursWeatherViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_hour_info_layout) LinearLayout mItemHourInfoLayout;
    private TextView[] mClock = new TextView[mWeather.getHourly_forecast().size()];
    private TextView[] mTemp = new TextView[mWeather.getHourly_forecast().size()];
    private TextView[] mHumidity = new TextView[mWeather.getHourly_forecast().size()];
    private TextView[] mWind = new TextView[mWeather.getHourly_forecast().size()];

    public HoursWeatherViewHolder(View inflate) {
      super(inflate);
      for (int i = 0; i < mWeather.getHourly_forecast().size(); i++) {
        View view = View.inflate(itemView.getContext(), R.layout.item_hour_info_line, null);
        mClock[i] = view.findViewById(R.id.one_clock);
        mTemp[i] = view.findViewById(R.id.one_temp);
        mHumidity[i] = view.findViewById(R.id.one_humidity);
        mWind[i] = view.findViewById(R.id.one_wind);
        mItemHourInfoLayout.addView(view);
      }
    }

    public void bind(Weather weather) {
      try {
        for (int i = 0; i < mWeather.getHourly_forecast().size(); i++) {
          String mDate = weather.getHourly_forecast().get(i).getDate();
          mClock[i].setText(mDate.substring(mDate.length() - 5, mDate.length()));
          mTemp[i].setText(String.format("%s℃", weather.getDaily_forecast().get(i).getTmp()));
          mHumidity[i].setText(String.format("%s%%", weather.getDaily_forecast().get(i).getHum()));
          mWind[i].setText(String.format("%sKm/h", weather.getDaily_forecast().get(i).getWind()));
        }
      } catch (Exception e) {
        GLog.e(TAG, e.getMessage());
      }
    }
  }

  /**
   * 当日建议
   */
  private class SuggestionViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.cloth_brf) TextView mClothBrf;
    @BindView(R.id.cloth_txt) TextView mClothTxt;
    @BindView(R.id.sport_brf) TextView mSportBrf;
    @BindView(R.id.sport_txt) TextView mSportTxt;
    @BindView(R.id.trave_brf) TextView mTraveBrf;
    @BindView(R.id.travel_txt) TextView mTravelTxt;
    @BindView(R.id.faver_brf) TextView mFaverBrf;
    @BindView(R.id.faver_txt) TextView mFaverTxt;

    public SuggestionViewHolder(View inflate) {
      super(inflate);
    }

    public void bind(Weather weather) {
      try {
        mClothBrf.setText(String.format("穿衣指数---%s", weather.getSuggestion().getDrsg().getBrf()));
        mClothTxt.setText(weather.getSuggestion().getDrsg().getTxt());

        mSportBrf.setText(String.format("运动指数---%s", weather.getSuggestion().getSport().getBrf()));
        mSportTxt.setText(weather.getSuggestion().getSport().getTxt());

        mTraveBrf.setText(String.format("旅游指数---%s", weather.getSuggestion().getTrav().getBrf()));
        mTravelTxt.setText(weather.getSuggestion().getTrav().getTxt());

        mFaverBrf.setText(String.format("感冒指数---%s", weather.getSuggestion().getFlu().getBrf()));
        mFaverTxt.setText(weather.getSuggestion().getFlu().getTxt());
      } catch (Exception e) {
        GLog.e(TAG, e.getMessage());
      }
    }
  }

  private class ForcastViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_forecast_info_layout) LinearLayout mItemForecastLayout;
    private TextView[] forecastDate = new TextView[mWeather.getHourly_forecast().size()];
    private TextView[] forecastTemp = new TextView[mWeather.getHourly_forecast().size()];
    private TextView[] forecastTxt = new TextView[mWeather.getHourly_forecast().size()];
    private ImageView[] forecastIcon = new ImageView[mWeather.getHourly_forecast().size()];

    public ForcastViewHolder(View inflate) {
      super(inflate);
      for (int i = 0; i < mWeather.getHourly_forecast().size(); i++) {
        View view = View.inflate(itemView.getContext(), R.layout.item_forecast_info_line, null);
        forecastDate[i] = view.findViewById(R.id.forecast_date);
        forecastTemp[i] = view.findViewById(R.id.forecast_temp);
        forecastTxt[i] = view.findViewById(R.id.forecast_txt);
        forecastIcon[i] = view.findViewById(R.id.forecast_icon);
        mItemForecastLayout.addView(view);
      }
    }

    public void bind(Weather weather) {
      try {
        forecastDate[0].setText("今日");
        forecastDate[1].setText("明日");
        for (int i = 0; i < weather.getDaily_forecast().size(); i++) {
          try {
            if (i > 1) {
              forecastDate[i].setText(
                  TimeUitl.dayForWeek(weather.getDaily_forecast().get(i).getDate()));
            }
          } catch (Exception e) {
            GLog.e(TAG, e.toString());
          }
          ImageLoader.load(mContext, SharedPreferenceUtil.getInstance()
                  .getInt(weather.getDaily_forecast().get(i).getCond().getTxt_d(), R.mipmap.none),
              forecastIcon[i]);

          forecastTemp[i].setText(
              String.format("%s℃ - %s℃", weather.getDaily_forecast().get(i).getTmp().getMin(),
                  weather.getDaily_forecast().get(i).getTmp().getMax()));

          forecastTxt[i].setText(String.format("%s。 %s %s %s km/h。降水几率为%s%%",
              weather.getDaily_forecast().get(i).getCond().getTxt_d(),
              weather.getDaily_forecast().get(i).getWind().getSc(),
              weather.getDaily_forecast().get(i).getWind().getDir(),
              weather.getDaily_forecast().get(i).getWind().getSpd(),
              weather.getDaily_forecast().get(i).getPop()));
        }
      } catch (Exception e) {
        GLog.e(TAG, e.getMessage());
      }
    }
  }
}
