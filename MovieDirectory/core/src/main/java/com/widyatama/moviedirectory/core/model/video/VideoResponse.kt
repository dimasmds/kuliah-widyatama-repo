package com.widyatama.moviedirectory.core.model.video

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VideoResponse {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("results")
    @Expose
    val results: List<Video>? = null

}
