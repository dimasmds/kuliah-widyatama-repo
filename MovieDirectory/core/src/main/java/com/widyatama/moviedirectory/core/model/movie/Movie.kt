package com.widyatama.moviedirectory.core.model.movie

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.widyatama.moviedirectory.core.model.generic.Genre
import com.widyatama.moviedirectory.core.model.generic.ProductionCompany
import com.widyatama.moviedirectory.core.model.generic.SpokenLanguage

class Movie() : Parcelable{

    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String? = null
    @SerializedName("genres")
    @Expose
    val genres: List<Genre>? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("overview")
    @Expose
    var overview: String? = null
    @SerializedName("poster_path")
    @Expose
    val posterPath: Any? = null
    @SerializedName("production_companies")
    @Expose
    val productionCompanies: List<ProductionCompany>? = null
    @SerializedName("release_date")
    @Expose
    val releaseDate: String? = null
    @SerializedName("runtime")
    @Expose
    val runtime: Int? = null
    @SerializedName("spoken_languages")
    @Expose
    val spokenLanguages: List<SpokenLanguage>? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("video")
    @Expose
    var video: Boolean? = null
    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double? = null
    @SerializedName("vote_count")
    @Expose
    val voteCount: Int? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        overview = parcel.readString()
        title = parcel.readString()
        video = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(overview)
        parcel.writeString(title)
        parcel.writeValue(video)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

}
