package com.znuri.mapro.ui.fragment.listchat


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.dynamic.ObjectWrapper
import com.sendbird.android.GroupChannel
import com.sendbird.android.OpenChannel

import com.znuri.mapro.R
import com.znuri.mapro.Utils.toGone
import com.znuri.mapro.Utils.toVisible
import com.znuri.mapro.services.Adapter.ListChatAdapter
import com.znuri.mapro.services.Fragment.BaseFragment
import com.znuri.mapro.ui.activity.chat.ChatActivity
import kotlinx.android.synthetic.main.fragment_list_chat.view.*
import kotlinx.android.synthetic.main.fragment_list_chat.view.rcListChannel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ListChatFragment : BaseFragment(), ListChatView.View , ListChatAdapter.OnSelectedRecyclerItemListener {

    override fun onClickItem(group: GroupChannel) {
        val bundle = Bundle()
        bundle.putString("url",group.url.toString())
        startActivity(Intent(context,ChatActivity::class.java).putExtras(bundle))
    }

    override fun getLayout(): Int {
        return R.layout.fragment_list_chat
    }

    lateinit var views: View
    lateinit var recylerView: RecyclerView
    lateinit var presenter:ListChatPresenter
    lateinit var listener:ListChatAdapter.OnSelectedRecyclerItemListener
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        views = inflater.inflate(R.layout.fragment_list_chat, container, false)
        listener = this
        initView()

        return views
    }

    fun initView() {
        presenter = ListChatPresenter()
        presenter.init(this)

        recylerView = views.rcListChannel
        presenter.getMyChannel()

        views.swipeToRefresh.setOnRefreshListener {
            Handler().postDelayed({
                views.swipeToRefresh.isRefreshing = false
                presenter.getMyChannel()
            },5000)
        }
    }

    override fun onSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailed() {
        Toast.makeText(context,"Failed To Load Please Try Again",Toast.LENGTH_LONG).show()
    }

    override fun onProgress() {
        views.loading.toVisible()
    }

    override fun onFetched(data: MutableList<GroupChannel>?) {
        views.loading.toGone()
        if(data?.size != 0){
            views.rcListChannel.toVisible()
            recylerView.apply {
                val adapters = ListChatAdapter(context,data!!)
                this.adapter = adapters
                this.layoutManager = LinearLayoutManager(context)
                adapters.initListener(listener)

            }
        } else {
            views.view_no_chat.toVisible()
        }
    }
}
