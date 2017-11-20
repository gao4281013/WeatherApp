package com.example.administrator.weatherpracticeapp.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/11/20.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
  public BaseViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  protected abstract void bind(T t);
}
