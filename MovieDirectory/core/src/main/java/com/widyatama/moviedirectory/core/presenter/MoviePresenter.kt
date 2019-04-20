package com.widyatama.moviedirectory.core.presenter

import com.widyatama.moviedirectory.core.BuildConfig
import com.widyatama.moviedirectory.core.api.APIRepository
import com.widyatama.moviedirectory.core.api.Client
import com.widyatama.moviedirectory.core.model.movie.Movie
import com.widyatama.moviedirectory.core.model.movie.MovieResponse
import com.widyatama.moviedirectory.core.utils.Constant
import com.widyatama.moviedirectory.core.view.MovieView

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviePresenter(private val view: MovieView) {

    fun searchMovie(query: String, page: Int?) {
        view.showMovieLoading()
        val apiRepository = Client.client.create(APIRepository::class.java)
        val call = apiRepository.serachMovie(BuildConfig.API_KEY, Constant.DEFAULT_MOVIE_LANGUAGE, query, page)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { it.results?.let { it1 -> view.showMovies(it1) } }
                    view.hideMovieLoading()
                } else {
                    view.showMovieError(response.message())
                    view.hideMovieLoading()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.message?.let { view.showMovieError(it) }
                view.hideMovieLoading()
            }
        })
    }

    fun getNowPlayingMovies(page: Int?) {
        view.showMovieLoading()
        val apiRepository = Client.client.create(APIRepository::class.java)
        val call = apiRepository.getNowPlayingMovies(BuildConfig.API_KEY, Constant.DEFAULT_MOVIE_LANGUAGE, page)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { it.results?.let { it1 -> view.showMovies(it1) } }
                    view.hideMovieLoading()
                } else {
                    view.showMovieError(response.message())
                    view.hideMovieLoading()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.message?.let { view.showMovieError(it) }
                view.hideMovieLoading()
            }
        })
    }

    fun getUpcomingMovies(page: Int?) {
        view.showMovieLoading()
        val apiRepository = Client.client.create(APIRepository::class.java)
        val call = apiRepository.getUpcomingMovies(BuildConfig.API_KEY, Constant.DEFAULT_MOVIE_LANGUAGE, page)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { it.results?.let { it1 -> view.showMovies(it1) } }
                    view.hideMovieLoading()
                } else {
                    view.showMovieError(response.message())
                    view.hideMovieLoading()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.message?.let { view.showMovieError(it) }
                view.hideMovieLoading()
            }
        })
    }

    fun getMovie(movieId: Int?) {
        view.showMovieLoading()
        val apiRepository = Client.client.create(APIRepository::class.java)
        val call = apiRepository.getMovie(movieId, BuildConfig.API_KEY, Constant.DEFAULT_MOVIE_LANGUAGE)
        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    response.body()?.let { view.showMovie(it) }
                    view.hideMovieLoading()
                } else {
                    view.showMovieError(response.message())
                    view.hideMovieLoading()
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                t.message?.let { view.showMovieError(it) }
                view.hideMovieLoading()
            }
        })
    }
}
