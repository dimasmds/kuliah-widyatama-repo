package com.widyatama.moviedirectory.core.view

import com.widyatama.moviedirectory.core.model.similar.Similar

interface SimilarView {
    fun showSimilarLoading()
    fun hideSimilarLoading()
    fun showSimilarError(message: String)
    fun showSimilarNotFound()
    fun showSimilarData(data: List<Similar>)
}
