package com.widyatama.moviedirectory.core.presenter

import com.widyatama.moviedirectory.core.BuildConfig
import com.widyatama.moviedirectory.core.api.APIRepository
import com.widyatama.moviedirectory.core.api.Client
import com.widyatama.moviedirectory.core.model.similar.SimilarResponse
import com.widyatama.moviedirectory.core.view.SimilarView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SimilarPresenter(private val view: SimilarView) {

    fun getSimilar(movieId: Int, page: Int) {
        view.showSimilarLoading()
        val apiRepository = Client.client.create(APIRepository::class.java)
        val call = apiRepository.getSimilar(movieId, BuildConfig.API_KEY, page)
        call.enqueue(object : Callback<SimilarResponse> {
            override fun onResponse(call: Call<SimilarResponse>, response: Response<SimilarResponse>) {
                if (response.isSuccessful) {
                    view.hideSimilarLoading()
                    val data = response.body()?.results
                    if (!data.isNullOrEmpty()) {
                        view.showSimilarData(data)
                    } else {
                        view.showSimilarNotFound()
                    }
                } else {
                    view.showSimilarError(response.message())
                    view.hideSimilarLoading()
                }
            }

            override fun onFailure(call: Call<SimilarResponse>, t: Throwable) {
                t.message?.let { view.showSimilarError(it) }
                view.hideSimilarLoading()
            }
        })
    }
}
