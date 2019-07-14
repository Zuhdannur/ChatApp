package com.znuri.mapro.services.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.sendbird.android.SendBird
import com.sendbird.android.User
import com.squareup.picasso.Picasso
import com.znuri.mapro.R
import com.znuri.mapro.Utils.toGone
import kotlinx.android.synthetic.main.item_all_user.view.*
import java.util.HashMap

class AllUserAdapter(val context: Context?, var data: MutableList<User>?) : RecyclerView.Adapter<AllUserAdapter.ViewHolder>() {

    constructor(): this(null,null)
    lateinit var listener:onRecyclerViewListener

    fun initInterface(listener:onRecyclerViewListener?){
        this.listener = listener!!
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_all_user,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var result = data?.get(position)

        holder?.itemView.playersName.text = result?.nickname
        Picasso.get().load(result?.profileUrl).resize(73,82).centerCrop().into(holder?.itemView.picProfile)
        var auth = FirebaseAuth.getInstance()
        if(auth.currentUser?.uid === result?.userId){
                holder?.itemView.btnUsers.toGone()
        }
        holder?.itemView.btnUsers.setOnClickListener {
            listener?.onClickItem(result!!)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    public interface onRecyclerViewListener{
        fun onClickItem(user:User)
    }
}