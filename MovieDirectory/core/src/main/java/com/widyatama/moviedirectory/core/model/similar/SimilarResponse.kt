package com.widyatama.moviedirectory.core.model.similar

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SimilarResponse {

    @SerializedName("results")
    @Expose
    val results: List<Similar>? = null

}
