package com.znuri.mapro.services.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.sendbird.android.GroupChannel
import com.sendbird.android.OpenChannel
import com.squareup.picasso.Picasso
import com.znuri.mapro.R
import kotlinx.android.synthetic.main.item_all_chat.view.*

class ListChatAdapter(val context: Context?,val data:MutableList<GroupChannel>?) : RecyclerView.Adapter<ListChatAdapter.ViewHolder>() {

    constructor() : this(null,null)
    lateinit var listener: OnSelectedRecyclerItemListener

    fun initListener(listener: OnSelectedRecyclerItemListener){
        this.listener = listener
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListChatAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_all_chat,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: ListChatAdapter.ViewHolder, position: Int) {
        val result = data?.get(position)
        var datas = result?.members

        if(datas?.get(0)?.userId == FirebaseAuth.getInstance().currentUser?.uid) {
            holder?.itemView.title.text = datas?.get(1)?.nickname
            Picasso.get().load(datas?.get(1)?.profileUrl).into(holder?.itemView.iconChat)
        }
        else{
            holder?.itemView.title.text = datas?.get(0)?.nickname
            Picasso.get().load(datas?.get(0)?.profileUrl).into(holder?.itemView.iconChat)
        }
        holder?.itemView.setOnClickListener {
            listener.onClickItem(result!!)
        }
    }

    public interface OnSelectedRecyclerItemListener{
        fun onClickItem(group:GroupChannel)
    }
}