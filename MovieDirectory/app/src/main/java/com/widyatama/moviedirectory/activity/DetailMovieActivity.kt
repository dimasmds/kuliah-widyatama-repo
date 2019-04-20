package com.widyatama.moviedirectory.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.widyatama.moviedirectory.BuildConfig
import com.widyatama.moviedirectory.R
import com.widyatama.moviedirectory.core.db.model.FavoriteMovie
import com.widyatama.moviedirectory.core.model.movie.Movie
import com.widyatama.moviedirectory.core.model.movie.Result
import com.widyatama.moviedirectory.core.model.video.Video
import com.widyatama.moviedirectory.core.presenter.FavoritePresenter
import com.widyatama.moviedirectory.core.presenter.MoviePresenter
import com.widyatama.moviedirectory.core.presenter.VideoPresenter
import com.widyatama.moviedirectory.core.view.FavoriteView
import com.widyatama.moviedirectory.core.view.MovieView
import com.widyatama.moviedirectory.core.view.VideoView
import com.widyatama.moviedirectory.fragment.SimilarFragment
import com.widyatama.moviedirectory.utils.ThemeUtils
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.layout_body_detail_movie.*
import kotlinx.android.synthetic.main.layout_header_detail_movie.*
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class DetailMovieActivity : AppCompatActivity(), MovieView, FavoriteView, VideoView {

    private var isFavorite: Boolean = false
    private var movieId: Int = 0
    private lateinit var movie: Movie
    private lateinit var favoritePresenter: FavoritePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtils.themeDetailState(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val movieTitle = intent.getStringExtra(TITLE_MOVIE)
        movieId = intent.getIntExtra(ID_MOVIE, 0)


        detailActivityTextViewToolbar.text = movieTitle

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.black)

        val presenter = MoviePresenter(this)
        presenter.getMovie(movieId)

        val videoPresenter = VideoPresenter(this, this)
        videoPresenter.getVideo(movieId)

        favoriteState()

        detailActivityBackButton.setOnClickListener { onBackPressed() }

        detailActivityFavoriteButton.setOnClickListener {
            if (!isFavorite) {
                if (favoritePresenter.addToFavorite(movie)) {
                    isFavorite = true
                    setFavorite()
                } else {
                    Toast.makeText(this@DetailMovieActivity, getString(R.string.message_fail_favorite), Toast.LENGTH_SHORT).show()
                }
            } else {
                if (favoritePresenter.removeFromFavorite(movieId)) {
                    isFavorite = false
                    setFavorite()
                } else {
                    Toast.makeText(this@DetailMovieActivity, getString(R.string.message_fail_add_favorite), Toast.LENGTH_SHORT).show()
                }
            }
        }

        val fragment = SimilarFragment.newInstance(movieId)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.detailActivityFrameLayout, fragment)
        transaction.commit()
    }

    private fun favoriteState() {
        favoritePresenter = FavoritePresenter(this, this)
        isFavorite = favoritePresenter.isFavorite(movieId)
        setFavorite()
    }

    private fun setFavorite() {
        if (isFavorite) {
            detailActivityFavoriteButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        } else {
            detailActivityFavoriteButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border))
        }
    }

    override fun showMovieLoading() {
        detailActivityProgressBar.visibility = View.VISIBLE
        detailActivityNestedScrollView.visibility = View.INVISIBLE
    }

    override fun hideMovieLoading() {
        detailActivityProgressBar.visibility = View.INVISIBLE
        detailActivityNestedScrollView.visibility = View.VISIBLE
    }

    override fun showMovieError(message: String) {
        toast(message)
    }

    override fun showMovies(data: List<Result>) {}

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun showMovie(data: Movie) {
        movie = data

        detailActivityTextViewTitle.text = data.title
        var dateFormat = SimpleDateFormat("yyyy-mm-dd")
        val date = dateFormat.parse(data.releaseDate)
        dateFormat = SimpleDateFormat("dd MMMM yyyy")

        detailActivityTextViewYear.text = dateFormat.format(date)

        detailActivityTextViewProduction.text = data.productionCompanies?.get(0)?.name ?: getString(R.string.unknown)

        detailActivityTextViewRating.text = data.voteAverage.toString()
        detailActivityTextViewOverview.text = data.overview
        detailActivityTextViewDuration.text = data.runtime.toString() + getString(R.string.minutes)

        Picasso.get().load(BuildConfig.IMAGE_BASE_URL + data.backdropPath).fit().centerCrop().into(detailActivityToolbarBackground)
        Picasso.get().load(BuildConfig.IMAGE_BASE_URL + data.posterPath).fit().centerCrop().into(detailActivityThumbnail)

        for (i in 0 until (data.spokenLanguages?.size ?: 0)) {
            if (i == 0)
                detailActivityTextViewLanguage.text = data.spokenLanguages?.get(i)?.name ?: getString(R.string.unknown)
            else
                detailActivityTextViewLanguage.text = detailActivityTextViewLanguage.text.toString() + ", " + (data.spokenLanguages?.get(i)?.name
                        ?: getString(R.string.unknown))
        }

        for (i in 0 until (data.genres?.size ?: 0)) {
            val category = TextView(this)

            val params = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            )

            params.marginEnd = 10

            category.layoutParams = params
            category.setTextColor(ContextCompat.getColor(applicationContext, android.R.color.white))
            category.setLines(1)
            category.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.alhpa))
            category.setPadding(10, 0, 10, 0)
            category.text = data.genres?.get(i)?.name ?: getString(R.string.unknown)
            detailActivityLinearLayoutCategory.addView(category)
        }
    }

    override fun onAdded(message: String) {
        toast(message)
    }

    override fun onDeleted(message: String) {
        toast(message)
    }

    override fun showFavoriteData(data: ArrayList<FavoriteMovie>) {}

    override fun showVideoData(video: Video) {
        detailActivityPlayVideo.visibility = View.VISIBLE
        detailActivityPlayVideo.setOnClickListener {
            val url = BuildConfig.VIDEO_BASE_URL + video.key
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    override fun showVideoNotFound(message: String) {
        toast(message)
        detailActivityPlayVideo.visibility = View.INVISIBLE
    }

    override fun showVideoError(message: String) {
        toast(message)
        detailActivityPlayVideo.visibility = View.INVISIBLE
    }

    companion object {
        const val TITLE_MOVIE = "titleMovie"
        const val ID_MOVIE = "idMovie"
    }
}
