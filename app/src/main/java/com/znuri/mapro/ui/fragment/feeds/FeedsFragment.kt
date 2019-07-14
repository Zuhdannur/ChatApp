package com.znuri.mapro.ui.fragment.feeds


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.znuri.mapro.R
import com.znuri.mapro.services.Adapter.FeedsAdapter
import com.znuri.mapro.services.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_feeds.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FeedsFragment : BaseFragment(), FeedsViews.View {

    override fun getLayout(): Int {
        return R.layout.fragment_feeds
    }

    lateinit var views: View
    lateinit var recylerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        views = inflater.inflate(R.layout.fragment_feeds, container, false)

        recylerView = views.rcFeeds
        initView()

        return views
    }

    fun initView() {
        recylerView.apply {
            this.adapter = FeedsAdapter(context)
            this.layoutManager = LinearLayoutManager(context)
        }
    }
}
