package com.widyatama.moviedirectory.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.widyatama.moviedirectory.R
import com.widyatama.moviedirectory.adapter.recyler.SimilarListAdapter
import com.widyatama.moviedirectory.core.model.similar.Similar
import com.widyatama.moviedirectory.core.presenter.SimilarPresenter
import com.widyatama.moviedirectory.core.view.SimilarView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class SimilarFragment : Fragment(), SimilarView {

    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_similar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.similarFragmentProgressBar)
        recyclerView = view.findViewById(R.id.similarFragmentRecyclerView)

        val movieId = Objects.requireNonNull<Bundle>(arguments).getInt(MOVIE_ID)
        val presenter = SimilarPresenter(this)
        presenter.getSimilar(movieId, 1)
    }

    override fun showSimilarLoading() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.INVISIBLE
    }

    override fun hideSimilarLoading() {
        progressBar.visibility = View.INVISIBLE
        recyclerView.visibility = View.VISIBLE
    }

    override fun showSimilarError(message: String) {
        toast(message)
    }

    override fun showSimilarNotFound() {
        toast(getString(R.string.message_similar_not_found))
    }

    override fun showSimilarData(data: List<Similar>) {
        if (context != null) {
            val layoutManager = LinearLayoutManager(ctx, LinearLayout.HORIZONTAL, false)
            val adapter = SimilarListAdapter(ctx, data)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
        }
    }

    companion object {

        const val MOVIE_ID = "movieId"

        fun newInstance(movieId: Int): SimilarFragment {
            val fragment = SimilarFragment()

            val bundle = Bundle()
            bundle.putInt(MOVIE_ID, movieId)
            fragment.arguments = bundle

            return fragment
        }
    }
}
