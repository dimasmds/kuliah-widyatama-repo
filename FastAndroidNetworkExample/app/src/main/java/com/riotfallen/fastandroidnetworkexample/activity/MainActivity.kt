package com.riotfallen.fastandroidnetworkexample.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.riotfallen.fastandroidnetworkexample.R
import com.riotfallen.fastandroidnetworkexample.adapter.LeagueAdapter
import com.riotfallen.fastandroidnetworkexample.model.League
import com.riotfallen.fastandroidnetworkexample.presenter.LeaguePresenter
import com.riotfallen.fastandroidnetworkexample.utils.invisible
import com.riotfallen.fastandroidnetworkexample.utils.visible
import com.riotfallen.fastandroidnetworkexample.view.LeagueView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), LeagueView {

    private  lateinit var leagueAdapter: LeagueAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        LeaguePresenter().getListLeague(this)
    }

    override fun showLoading() {
        mainActivityProgressBar.visible()
        mainActivityRecyclerView.invisible()
    }

    override fun hideLoading() {
        mainActivityProgressBar.invisible()
        mainActivityRecyclerView.visible()
    }

    override fun showListLeague(data: MutableList<League>) {
        leagueAdapter = LeagueAdapter(this, data)

        mainActivityRecyclerView.layoutManager  = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mainActivityRecyclerView.adapter = leagueAdapter
    }
}
