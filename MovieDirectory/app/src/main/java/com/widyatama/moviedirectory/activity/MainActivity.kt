package com.widyatama.moviedirectory.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.widyatama.moviedirectory.R
import com.widyatama.moviedirectory.adapter.pager.MoviePagerAdapter
import com.widyatama.moviedirectory.utils.ThemeUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtils.themeState(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(mainActivityToolbar)
        supportActionBar?.title = ""

        val actionBarDrawerToggle = ActionBarDrawerToggle(
                this, mainActivityDrawerLayout, mainActivityToolbar, R.string.drawer_open, R.string.drawer_close)

        mainActivityDrawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        mainActivityNavigationView.setCheckedItem(R.id.menuNowPlaying)
        mainActivityNavigationView.setNavigationItemSelectedListener(this)

        mainActivityFAB.bringToFront()
        mainActivityFAB.setOnClickListener { startActivity(Intent(this@MainActivity, SearchActivity::class.java)) }

        mainActivityTabLayout.addTab(mainActivityTabLayout.newTab().setText(R.string.now_playing))
        mainActivityTabLayout.addTab(mainActivityTabLayout.newTab().setText(R.string.upcoming))
        mainActivityTabLayout.addTab(mainActivityTabLayout.newTab().setText(R.string.favorite))

        val pagerAdapter = MoviePagerAdapter(supportFragmentManager, mainActivityTabLayout.tabCount)
        mainActivityViewPager.adapter = pagerAdapter
        mainActivityViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mainActivityTabLayout))

        mainActivityTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                mainActivityViewPager.currentItem = tab.position
                when (mainActivityViewPager.currentItem) {
                    0 -> mainActivityNavigationView.setCheckedItem(R.id.menuNowPlaying)
                    1 -> mainActivityNavigationView.setCheckedItem(R.id.menuUpcoming)
                    2 -> mainActivityNavigationView.setCheckedItem(R.id.menuFavorite)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        menuMainChangeLanguage.setOnClickListener {
            val intent = Intent(this@MainActivity, SettingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        mainActivityDrawerLayout.closeDrawer(GravityCompat.START)

        when (menuItem.itemId) {
            R.id.menuNowPlaying -> mainActivityViewPager.currentItem = 0
            R.id.menuUpcoming -> mainActivityViewPager.currentItem = 1
            R.id.menuFavorite -> mainActivityViewPager.currentItem = 2
            R.id.menuSearch -> startActivity(Intent(this@MainActivity, SearchActivity::class.java))
        }

        return true
    }

    override fun onBackPressed() {
        if (mainActivityDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mainActivityDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}