package com.example.administrator.weatherpracticeapp.modules.main

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.administrator.weatherpracticeapp.R
import com.example.administrator.weatherpracticeapp.event.SplashEvent
import com.example.administrator.weatherpracticeapp.modules.main.adapter.HomePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.part_tab_layout.*
import org.greenrobot.eventbus.EventBus

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
  private var mHomeFragment: MainFragment? = null
  private var mMutiFragment: MultiCityFragment? = null
  private var mAdapter:HomePagerAdapter?=null;

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)
    EventBus.getDefault().post(SplashEvent())
    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show()
    }

    val toggle = ActionBarDrawerToggle(
        this, drawer_layout, toolbar,
        R.string.navigation_drawer_open,
        R.string.navigation_drawer_close)
    drawer_layout.addDrawerListener(toggle)
    toggle.syncState()

    nav_view.setNavigationItemSelectedListener(this)
    init()
  }

  private fun init() {
    mHomeFragment = MainFragment()
    mMutiFragment = MultiCityFragment()
    nav_view.setCheckedItem(R.id.nav_city)
    mAdapter = HomePagerAdapter(supportFragmentManager)
    mAdapter!!.addTab(mHomeFragment,"主页面")
    mAdapter!!.addTab(mMutiFragment,"多城市")
    view_pager.adapter = mAdapter
    tab_layout.setupWithViewPager(view_pager)
    view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
      override fun onPageSelected(position: Int) {
        if (fab.isShown()) {
          fabVisibilityChangedListener.position = position
          mFab.hide(fabVisibilityChangedListener)
        } else {
          changeFabState(position)
          mFab.show()
        }
      }
    })
  }

  private class FabVisibilityChangedListener : FloatingActionButton.OnVisibilityChangedListener(){
    override fun onShown(fab: FloatingActionButton?) {
      super.onShown(fab)
    }

    override fun onHidden(fab: FloatingActionButton?) {
      super.onHidden(fab)
    }
  }


  override fun onBackPressed() {
    if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
      drawer_layout.closeDrawer(GravityCompat.START)
    } else {
      super.onBackPressed()
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    when (item.itemId) {
      R.id.action_settings -> return true
      else -> return super.onOptionsItemSelected(item)
    }
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    // Handle navigation view item clicks here.
    when (item.itemId) {
      R.id.nav_city -> {
        // Handle the camera action
      }
      R.id.nav_muti_city -> {

      }
      R.id.nav_set -> {

      }
      R.id.nav_about -> {

      }
    }

    drawer_layout.closeDrawer(GravityCompat.START)
    return true
  }
}
