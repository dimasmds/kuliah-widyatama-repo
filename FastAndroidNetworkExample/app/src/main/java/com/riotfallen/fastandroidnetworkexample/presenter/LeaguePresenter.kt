package com.riotfallen.fastandroidnetworkexample.presenter

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.riotfallen.fastandroidnetworkexample.BuildConfig
import com.riotfallen.fastandroidnetworkexample.model.LeagueResponse
import com.riotfallen.fastandroidnetworkexample.view.BadgeView
import com.riotfallen.fastandroidnetworkexample.view.LeagueView

class LeaguePresenter {

    fun getListLeague(view: LeagueView){
        view.showLoading()
        AndroidNetworking.get(BuildConfig.BASE_URL + BuildConfig.TSDB_API_KEY + "/all_leagues.php")
            .setTag(this)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsObject(LeagueResponse::class.java, object : ParsedRequestListener<LeagueResponse> {
                override fun onResponse(response: LeagueResponse) {
                    view.hideLoading()
                    response.leagues?.let { view.showListLeague(it) }
                }

                override fun onError(anError: ANError?) {
                    view.hideLoading()
                    Log.d("RESPONSE", anError?.message)
                }

            })
    }

    fun getLeagueBadge(view: BadgeView, idLeague: String){
        AndroidNetworking.get(BuildConfig.BASE_URL + BuildConfig.TSDB_API_KEY + "/lookupleague.php")
            .addQueryParameter("id", idLeague)
            .setTag(this)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsObject(LeagueResponse::class.java, object : ParsedRequestListener<LeagueResponse> {
                override fun onResponse(response: LeagueResponse) {
                    response.leagues?.get(0)?.strBadge?.let { view.showBadge(it) }
                }

                override fun onError(anError: ANError?) {
                    Log.d("RESPONSE", anError?.message)
                }

            })
    }

}