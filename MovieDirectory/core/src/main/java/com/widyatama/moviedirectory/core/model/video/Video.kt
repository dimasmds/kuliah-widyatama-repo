package com.widyatama.moviedirectory.core.model.video

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Video {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("key")
    @Expose
    val key: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("size")
    @Expose
    var size: Int? = null
    @SerializedName("type")
    @Expose
    var type: String? = null

}
