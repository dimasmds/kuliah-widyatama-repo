package com.riotfallen.fastandroidnetworkexample.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.riotfallen.fastandroidnetworkexample.R
import com.riotfallen.fastandroidnetworkexample.model.League
import com.riotfallen.fastandroidnetworkexample.presenter.LeaguePresenter
import com.riotfallen.fastandroidnetworkexample.view.BadgeView
import com.squareup.picasso.Picasso

class LeagueAdapter(private val context: Context, private val data: MutableList<League>)
    : RecyclerView.Adapter<LeagueAdapter.LeagueListViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueListViewHolder {
        return LeagueListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_league_list, parent, false))
    }

    override fun getItemCount(): Int  = data.size

    override fun onBindViewHolder(holder: LeagueListViewHolder, position: Int) {
        holder.bindItem(data[position])
    }

    override fun getItemViewType(position: Int): Int = position



    class LeagueListViewHolder(view : View) : RecyclerView.ViewHolder(view), BadgeView {
        private val imageViewBadge: ImageView = view.findViewById(R.id.illImageViewBadge)
        private val textViewNameLeague: TextView = view.findViewById(R.id.illTextViewLeagueName)
        private val textViewSportType: TextView = view.findViewById(R.id.illTextViewSportType)

        fun bindItem(league: League){
            textViewNameLeague.text = league.strLeague
            textViewSportType.text = league.strSport
            league.idLeague?.let { LeaguePresenter().getLeagueBadge(this, it) }

        }

        override fun showBadge(badge: String) {
            Picasso.get().load(badge).fit().centerCrop().into(imageViewBadge)
        }
    }


}