package com.example.administrator.weatherpracticeapp.modules.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/29.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {

  private List<Fragment> mFragments = new ArrayList<>();
  private List<String> titles = new ArrayList<>();

  public HomePagerAdapter(FragmentManager fm) {
    super(fm);
  }

  public void addTab(Fragment fragment,String title){
     mFragments.add(fragment);
     titles.add(title);
  }

  @Override
  public Fragment getItem(int position) {
    return mFragments.get(position);
  }

  @Override
  public int getCount() {
    return mFragments.size();
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return titles.get(position);
  }
}
