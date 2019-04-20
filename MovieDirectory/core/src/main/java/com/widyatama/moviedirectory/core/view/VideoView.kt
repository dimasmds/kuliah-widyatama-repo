package com.widyatama.moviedirectory.core.view

import com.widyatama.moviedirectory.core.model.video.Video

interface VideoView {
    fun showVideoData(video: Video)
    fun showVideoNotFound(message: String)
    fun showVideoError(message: String)
}
