package com.widyatama.moviedirectory.core.model.movie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieResponse {

    @SerializedName("results")
    @Expose
    val results: List<Result>? = null

}
