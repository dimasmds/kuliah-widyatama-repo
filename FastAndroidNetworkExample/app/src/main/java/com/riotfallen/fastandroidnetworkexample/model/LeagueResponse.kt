package com.riotfallen.fastandroidnetworkexample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LeagueResponse (

    @SerializedName("leagues")
    @Expose
    var leagues: MutableList<League>? = null

)