package com.widyatama.moviedirectory.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.widyatama.moviedirectory.R
import com.widyatama.moviedirectory.adapter.recyler.MovieListAdapter
import com.widyatama.moviedirectory.core.model.movie.Movie
import com.widyatama.moviedirectory.core.model.movie.Result
import com.widyatama.moviedirectory.core.presenter.MoviePresenter
import com.widyatama.moviedirectory.core.view.MovieView
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.toast
import java.util.*

class SearchActivity : AppCompatActivity(), MovieView {

    private lateinit var moviePresenter: MoviePresenter
    private lateinit var movies: MutableList<Result>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        movies = ArrayList()
        moviePresenter = MoviePresenter(this)

        searchActivityEditText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (searchActivityEditText.text.toString().trim().isEmpty()) {
                    Toast.makeText(this.applicationContext, getString(R.string.empty_query), Toast.LENGTH_SHORT).show()
                } else {
                    movies.clear()
                    val query = searchActivityEditText.text.toString()
                    moviePresenter.searchMovie(query, 1)

                    //Close soft keyboard
                    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
                }

                return@OnEditorActionListener true
            }
            false
        })
    }

    override fun showMovieLoading() {
        if (searchActivityEmptyView.visibility == View.VISIBLE) {
            searchActivityEmptyView.visibility = View.GONE
        }
        searchActivityProgressBar.visibility = View.VISIBLE
        searchActivityRecyclerView.visibility = View.INVISIBLE
    }

    override fun hideMovieLoading() {
        searchActivityProgressBar.visibility = View.INVISIBLE
        searchActivityRecyclerView.visibility = View.VISIBLE
    }

    override fun showMovieError(message: String) {
        toast(message)
    }

    override fun showMovies(data: List<Result>) {
        data.let { movies.addAll(it) }
        if (movies.size > 0) {
            val movieListAdapter = MovieListAdapter(this, movies)
            searchActivityRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
            searchActivityRecyclerView.adapter = movieListAdapter

            searchActivityRecyclerView.visibility = View.VISIBLE
        } else {
            searchActivityRecyclerView.visibility = View.GONE
            searchActivityEmptyView.visibility = View.VISIBLE
        }
    }

    override fun showMovie(data: Movie) {}
}
