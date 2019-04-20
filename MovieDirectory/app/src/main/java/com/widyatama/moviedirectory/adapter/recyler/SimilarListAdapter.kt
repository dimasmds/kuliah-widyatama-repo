package com.widyatama.moviedirectory.adapter.recyler

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.widyatama.moviedirectory.BuildConfig
import com.widyatama.moviedirectory.R
import com.widyatama.moviedirectory.activity.DetailMovieActivity
import com.widyatama.moviedirectory.core.model.similar.Similar
import com.squareup.picasso.Picasso

class SimilarListAdapter(private val context: Context, private val similars: List<Similar>) : RecyclerView.Adapter<SimilarListAdapter.SimilarListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SimilarListAdapter.SimilarListViewHolder {
        return SimilarListViewHolder(LayoutInflater.from(context).inflate(R.layout.recyler_item_similar, viewGroup, false))
    }

    override fun onBindViewHolder(similarListViewHolder: SimilarListAdapter.SimilarListViewHolder, position: Int) {
        similarListViewHolder.bindItem(similars[position])
        similarListViewHolder.itemView.setOnClickListener {
            val intent = Intent(context, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.TITLE_MOVIE, similars[position].title)
            intent.putExtra(DetailMovieActivity.ID_MOVIE, similars[position].id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return similars.size
    }


    inner class SimilarListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var imageView = itemView.findViewById<ImageView>(R.id.risImageViewPoster)
        private var textViewTitle = itemView.findViewById<TextView>(R.id.risTextViewTitle)
        private var textViewRating = itemView.findViewById<TextView>(R.id.risTextViewRating)

        fun bindItem(similar: Similar) {
            textViewTitle.text = similar.title
            textViewRating.text = String.format(context.resources.getString(R.string.dummy_rating), similar.voteAverage)
            Picasso.get().load(BuildConfig.IMAGE_BASE_URL + similar.posterPath).fit().centerCrop().into(imageView)
        }
    }
}
