package com.widyatama.moviedirectory.core.db.model

import android.os.Parcel
import android.os.Parcelable

class FavoriteMovie() : Parcelable {

    var id: Int? = 0
    var movieId: Int? = 0
    var movieTitle: String? = null
    var movieOverview: String? = null
    var movieRating: Double? = 0.toDouble()
    var movieVoter: Int? = 0
    var movieBackdrop: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        movieId = parcel.readValue(Int::class.java.classLoader) as? Int
        movieTitle = parcel.readString()
        movieOverview = parcel.readString()
        movieRating = parcel.readValue(Double::class.java.classLoader) as? Double
        movieVoter = parcel.readValue(Int::class.java.classLoader) as? Int
        movieBackdrop = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeValue(movieId)
        parcel.writeString(movieTitle)
        parcel.writeString(movieOverview)
        parcel.writeValue(movieRating)
        parcel.writeValue(movieVoter)
        parcel.writeString(movieBackdrop)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FavoriteMovie> {
        override fun createFromParcel(parcel: Parcel): FavoriteMovie {
            return FavoriteMovie(parcel)
        }

        override fun newArray(size: Int): Array<FavoriteMovie?> {
            return arrayOfNulls(size)
        }
    }
}
