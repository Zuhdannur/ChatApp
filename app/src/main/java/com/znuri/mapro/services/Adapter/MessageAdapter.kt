package com.znuri.mapro.services.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sendbird.android.BaseMessage
import com.sendbird.android.SendBird
import com.sendbird.android.UserMessage
import com.squareup.picasso.Picasso
import com.znuri.mapro.R
import com.znuri.mapro.Utils.toGone
import com.znuri.mapro.Utils.toVisible
import kotlinx.android.synthetic.main.item_room_chat.view.*

class MessageAdapter(val context: Context?,val data:List<BaseMessage>?) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    constructor() : this(null,null)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_room_chat,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var result:UserMessage = data?.get(position) as UserMessage
        if(result.sender.userId == SendBird.getCurrentUser().userId){
            holder?.itemView.reciever.toVisible()
            holder?.itemView.sender.toGone()
            holder?.itemView.reciever.text = result.message
        } else {
            holder?.itemView.sender.toVisible()
            holder?.itemView.reciever.toGone()
            Picasso.get().load(result.sender.profileUrl).into(holder?.itemView.picProfile)
            holder?.itemView.sender.text = result.message
            holder?.itemView.playersName.text = result.sender.nickname
        }
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)
}