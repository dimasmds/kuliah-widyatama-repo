package com.widyatama.moviedirectory.adapter.pager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

import com.widyatama.moviedirectory.fragment.MovieListFragment

class MoviePagerAdapter(fm: FragmentManager, private val noOfTabs: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return MovieListFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return noOfTabs
    }
}
