package com.widyatama.moviedirectory.adapter.recyler

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.widyatama.moviedirectory.BuildConfig
import com.widyatama.moviedirectory.R
import com.widyatama.moviedirectory.activity.DetailMovieActivity
import com.widyatama.moviedirectory.core.db.model.FavoriteMovie
import com.widyatama.moviedirectory.core.model.movie.Result
import com.widyatama.moviedirectory.core.presenter.FavoritePresenter
import com.widyatama.moviedirectory.core.view.FavoriteView
import com.squareup.picasso.Picasso

import java.util.ArrayList

class MovieListAdapter(private val context: Context, private val movies: List<Result>) : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>(), FavoriteView {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MovieListAdapter.MovieListViewHolder {
        return MovieListViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item_movie, viewGroup, false))
    }

    override fun onBindViewHolder(movieListViewHolder: MovieListAdapter.MovieListViewHolder, position: Int) {
        movieListViewHolder.bindItem(movies[position])
        movieListViewHolder.itemView.setOnClickListener {
            val intent = Intent(context, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.TITLE_MOVIE, movies[position].title)
            intent.putExtra(DetailMovieActivity.ID_MOVIE, movies[position].id)
            context.startActivity(intent)
        }

        val presenter = FavoritePresenter(context, this)

        if (!presenter.isFavorite(movies[position].id!!)) {
            movieListViewHolder.linearLayout.visibility = View.GONE
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onAdded(message: String) {}

    override fun onDeleted(message: String) {}

    override fun showFavoriteData(data: ArrayList<FavoriteMovie>) {}

    inner class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageViewThumb = itemView.findViewById<ImageView>(R.id.rimImageViewThumbnail)
        private val textViewTitle = itemView.findViewById<TextView>(R.id.rimTextViewTitle)
        private val textViewLanguage = itemView.findViewById<TextView>(R.id.rimTexTViewLanguage)
        private val textViewRating = itemView.findViewById<TextView>(R.id.rimTextViewRating)
        private val textViewVote = itemView.findViewById<TextView>(R.id.rimTextViewVoting)
        val linearLayout = itemView.findViewById<LinearLayout>(R.id.rimLinearLayoutAddFavorite)!!

        fun bindItem(movie: Result) {
            textViewTitle.text = movie.title
            textViewRating.text = String.format(context.resources.getString(R.string.dummy_rating), movie.voteAverage)
            textViewVote.text = String.format(context.resources.getString(R.string.voting), movie.voteCount)
            Picasso.get().load(BuildConfig.IMAGE_BASE_URL + movie.backdropPath).fit().centerCrop().into(imageViewThumb)
            textViewLanguage.text = movie.overview
        }

    }
}
