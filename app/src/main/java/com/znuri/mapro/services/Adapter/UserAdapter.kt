package com.znuri.mapro.services.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sendbird.android.User
import com.squareup.picasso.Picasso
import com.znuri.mapro.R
import kotlinx.android.synthetic.main.item_user.view.*

open class UserAdapter(val context: Context?,var data: MutableList<User>?) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    constructor(): this(null,null)
    lateinit var listener: OnRecyclerViewListener

    fun init(listener: OnRecyclerViewListener?){
        this.listener = listener!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var result = data?.get(position)

        holder?.itemView.playersName.text = result?.nickname
        holder?.itemView.status.text = result?.connectionStatus.toString()
        Picasso.get().load(result?.profileUrl).resize(73,82).centerCrop().into(holder?.itemView.picProfile)

        holder?.itemView.setOnClickListener {
            listener?.onClickItem(result!!)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    public interface OnRecyclerViewListener{
        fun onClickItem(user: User?)
    }
}