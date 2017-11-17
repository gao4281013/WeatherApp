package com.example.administrator.weatherpracticeapp.base;

import android.content.Context;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.github.moduth.blockcanary.internal.BlockInfo;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/17.
 */

public class AppBlockCanaryContext extends BlockCanaryContext {

  @Override
  public Context provideContext() {
    return super.provideContext();
  }

  @Override
  public String provideQualifier() {
    return "unKnown";
  }

  @Override
  public String provideUid() {
    return "uId";
  }

  @Override
  public String provideNetworkType() {
    return "unKnown";
  }

  @Override
  public int provideMonitorDuration() {
    return -1;
  }

  @Override
  public int provideBlockThreshold() {
    return 1000;
  }

  @Override
  public int provideDumpInterval() {
    return provideBlockThreshold();
  }

  @Override
  public String providePath() {
    return "/blockcanary/";
  }

  @Override
  public boolean displayNotification() {
    return true;
  }

  @Override
  public boolean zip(File[] src, File dest) {
    return false;
  }

  @Override
  public void upload(File zippedFile) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<String> concernPackages() {
    return null;
  }

  @Override
  public boolean filterNonConcernStack() {
    return false;
  }

  @Override
  public List<String> provideWhiteList() {
    LinkedList<String> whiteList = new LinkedList<>();
    whiteList.add("org.chromium");
    return whiteList;
  }

  @Override
  public boolean deleteFilesInWhiteList() {
    return true;
  }

  @Override
  public void onBlock(Context context, BlockInfo blockInfo) {
    super.onBlock(context, blockInfo);
  }
}
