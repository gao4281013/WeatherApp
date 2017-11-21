package com.example.administrator.weatherpracticeapp.common;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2017/11/21.
 */

public class ImageLoader {

  public static void load(Context context,int imgRes,ImageView view){
    Glide.with(context).load(imgRes).crossFade().into(view);
  }

  public static void clear(Context context){
    Glide.get(context).clearMemory();
  }
}
