package com.riotfallen.fastandroidnetworkexample.view

import com.riotfallen.fastandroidnetworkexample.model.League

interface LeagueView {

    fun showLoading()
    fun hideLoading()
    fun showListLeague(data: MutableList<League>)

}