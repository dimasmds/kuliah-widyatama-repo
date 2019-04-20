package com.widyatama.moviedirectory.core.presenter

import android.content.Context
import com.widyatama.moviedirectory.core.BuildConfig
import com.widyatama.moviedirectory.core.R
import com.widyatama.moviedirectory.core.api.APIRepository
import com.widyatama.moviedirectory.core.api.Client
import com.widyatama.moviedirectory.core.model.video.VideoResponse
import com.widyatama.moviedirectory.core.view.VideoView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoPresenter(private val videoView: VideoView, private val context: Context) {

    fun getVideo(movieId: Int) {
        val repository = Client.client.create(APIRepository::class.java)
        val call = repository.getVideos(movieId, BuildConfig.API_KEY)
        call.enqueue(object : Callback<VideoResponse> {
            override fun onResponse(call: Call<VideoResponse>, response: Response<VideoResponse>) {
                if (response.isSuccessful) {
                    val data = response.body()?.results
                    if (!data.isNullOrEmpty()) {
                        videoView.showVideoData(data[0])
                    } else {
                        videoView.showVideoNotFound(context.getString(R.string.message_video_not_found))
                    }
                } else {
                    videoView.showVideoError(response.message())
                }
            }

            override fun onFailure(call: Call<VideoResponse>, t: Throwable) {
                t.message?.let { videoView.showVideoError(it) }
            }
        })
    }
}
