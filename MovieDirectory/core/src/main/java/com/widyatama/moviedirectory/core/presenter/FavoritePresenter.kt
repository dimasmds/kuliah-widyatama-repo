package com.widyatama.moviedirectory.core.presenter

import android.content.Context
import android.database.SQLException
import android.util.Log
import com.widyatama.moviedirectory.core.R
import com.widyatama.moviedirectory.core.db.helper.MovieHelper
import com.widyatama.moviedirectory.core.db.model.FavoriteMovie
import com.widyatama.moviedirectory.core.model.movie.Movie
import com.widyatama.moviedirectory.core.view.FavoriteView

class FavoritePresenter(private val context: Context, private val view: FavoriteView) {

    private val movieHelper: MovieHelper = MovieHelper(context)

    fun isFavorite(movieId: Int): Boolean {
        movieHelper.open()
        val cursor = movieHelper.queryById(movieId)
        val isExists = cursor.count <= 0
        cursor.close()
        movieHelper.close()
        return !isExists
    }


    fun showFavoriteData() {
        movieHelper.open()
        try {
            view.showFavoriteData(movieHelper.query())
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        movieHelper.close()
    }

    fun addToFavorite(result: Movie): Boolean {
        movieHelper.open()
        val movie = FavoriteMovie()

        // assigned result movie from server to local FavoriteMovie model
        movie.movieId = result.id
        movie.movieTitle = result.title
        movie.movieBackdrop = result.backdropPath
        movie.movieVoter = result.voteCount
        movie.movieOverview = result.overview
        movie.movieRating = result.voteAverage

        val insert = movieHelper.insert(movie)
        movieHelper.close()
        Log.d("addToFavorite", insert.toString())
        return if (insert.compareTo(-1) != 0) {
            view.onAdded(context.getString(R.string.message_favorite_added))
            true
        } else {
            false
        }
    }

    fun removeFromFavorite(movieId: Int): Boolean {
        movieHelper.open()
        val deletedRow = movieHelper.delete(movieId)
        movieHelper.close()
        Log.d("removeFromFavorite", movieId.toString())
        return if (deletedRow > 0) {
            view.onDeleted(context.getString(R.string.message_delete_favorite))
            true
        } else {
            false
        }
    }
}
