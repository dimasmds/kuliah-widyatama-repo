package com.widyatama.moviedirectory.core.db.helper

import android.net.Uri
import android.provider.BaseColumns

internal object DatabaseContract {

    var TABLE_FAVORITE = "table_favorite"
    var AUTHORITY = "com.widyatama.moviedirectory.core"


    val CONTENT_URI = Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_FAVORITE)
            .build()

    internal class MovieColumn : BaseColumns {
        companion object {

            var COLUMN_MOVIE_ID = "movieId"
            var COLUMN_MOVIE_TITLE = "movieTitle"
            var COLUMN_MOVIE_OVERVIEW = "movieOverview"
            var COLUMN_MOVIE_RATING = "movieRating"
            var COLUMN_MOVIE_VOTER = "movieVoter"
            var COLUMN_MOVIE_BACKDROP = "movieBackdrop"
        }
    }

}
