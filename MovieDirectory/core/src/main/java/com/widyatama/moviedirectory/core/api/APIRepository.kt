package com.widyatama.moviedirectory.core.api

import com.widyatama.moviedirectory.core.model.movie.Movie
import com.widyatama.moviedirectory.core.model.movie.MovieResponse
import com.widyatama.moviedirectory.core.model.similar.SimilarResponse
import com.widyatama.moviedirectory.core.model.video.VideoResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIRepository {


    @GET("search/movie")
    fun serachMovie(@Query("api_key") apiKey: String,
                    @Query("language") language: String,
                    @Query("query") query: String,
                    @Query("page") page: Int?): Call<MovieResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("api_key") apiKey: String,
                            @Query("language") language: String,
                            @Query("page") page: Int?): Call<MovieResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") apiKey: String,
                          @Query("language") language: String,
                          @Query("page") page: Int?): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovie(@Path("movie_id") movieId: Int?,
                 @Query("api_key") apiKey: String,
                 @Query("language") language: String): Call<Movie>

    @GET("movie/{movie_id}/similar")
    fun getSimilar(@Path("movie_id") movieId: Int?,
                   @Query("api_key") apiKey: String,
                   @Query("page") page: Int?): Call<SimilarResponse>

    @GET("movie/{movie_id}/videos")
    fun getVideos(@Path("movie_id") movieId: Int?,
                  @Query("api_key") apiKey: String): Call<VideoResponse>
}
