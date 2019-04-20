package com.widyatama.moviedirectory.core.view

import com.widyatama.moviedirectory.core.db.model.FavoriteMovie

import java.util.ArrayList

interface FavoriteView {
    fun onAdded(message: String)
    fun onDeleted(message: String)
    fun showFavoriteData(data: ArrayList<FavoriteMovie>)
}
