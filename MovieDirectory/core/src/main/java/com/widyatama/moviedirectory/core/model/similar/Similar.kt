package com.widyatama.moviedirectory.core.model.similar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Similar {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("overview")
    @Expose
    var overview: String? = null
    @SerializedName("poster_path")
    @Expose
    val posterPath: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("video")
    @Expose
    var video: Boolean? = null
    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double? = null

}
