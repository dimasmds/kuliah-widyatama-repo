package com.widyatama.moviedirectory.core.model.movie

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result() : Parcelable {

    @SerializedName("vote_count")
    @Expose
    val voteCount: Int? = null
    @SerializedName("id")
    @Expose
    val id: Int? = null
    @SerializedName("video")
    @Expose
    val video: Boolean? = null
    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double? = null
    @SerializedName("title")
    @Expose
    val title: String? = null
    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String? = null
    @SerializedName("overview")
    @Expose
    val overview: String? = null

    @SerializedName("release_date")
    @Expose
    val releaseDate: String? = null

    @Suppress("UNUSED_PARAMETER")
    constructor(parcel: Parcel) : this()

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }

}
