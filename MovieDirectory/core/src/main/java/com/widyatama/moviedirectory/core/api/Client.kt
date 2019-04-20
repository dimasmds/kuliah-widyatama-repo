package com.widyatama.moviedirectory.core.api

import com.widyatama.moviedirectory.core.BuildConfig

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {

    val client: Retrofit
        get() = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

}
