package com.znuri.mapro.services.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.znuri.mapro.R
import kotlinx.android.synthetic.main.item_all_feed.view.*

class FeedsAdapter(val context: Context?) : RecyclerView.Adapter<FeedsAdapter.ViewHolder>() {
    constructor(): this(null)
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedsAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_all_feed,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: FeedsAdapter.ViewHolder, position: Int) {
        Picasso.get().load("https://res.klook.com/images/fl_lossy.progressive,q_65/c_fill,w_1295,h_720,f_auto/w_80,x_15,y_15,g_south_west,l_klook_water/activities/6522cf6a-/BalonUdaraSunrise.jpg")
            .into(holder?.itemView.feedImage)
    }
}