package com.znuri.mapro.ui.activity.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sendbird.android.SendBirdException
import com.sendbird.android.User
import com.znuri.mapro.R
import com.znuri.mapro.Utils.getToken
import com.znuri.mapro.services.Fragment.BaseFragment
import com.znuri.mapro.services.Adapter.UserAdapter
import com.znuri.mapro.ui.fragment.feeds.FeedsFragment
import com.znuri.mapro.ui.fragment.freind.FriendFragment
import com.znuri.mapro.ui.fragment.listchat.ListChatFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), HomeView.View {

    override fun setFragment(fragment: BaseFragment?) {
        fragment?.attachPresenter(presenter)

        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content,fragment)
                .commit()
        }
    }

    override fun onProgress() {
//        loading.toVisible()
    }

    override fun onFetched(mutableList: MutableList<User>?) {
//        loading.toGone()
//        if(mutableList?.size != 0){
//            rcUsers.toVisible()
//            adapter = UserAdapter(applicationContext,mutableList)
//            rcUsers.apply {
//                recylerView.adapter = UserAdapter(applicationContext,mutableList)
//                recylerView.layoutManager = LinearLayoutManager(applicationContext!!)
//                recylerView.addItemDecoration(DividerItemDecoration(applicationContext,DividerItemDecoration.HORIZONTAL))
//            }
//        } else {
//            rcUsers.toGone()
//
//            view_no_freind.toVisible()
//        }
    }

    lateinit var presenter: HomePresenter
    lateinit var adapter:UserAdapter

    lateinit var recylerView:RecyclerView
    override fun onSuccses() {
        Toast.makeText(applicationContext, "Success Connect",Toast.LENGTH_LONG).show()
    }

    override fun onFailed(message: SendBirdException?) {
        presenter.connectToServer(getToken(this@HomeActivity))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initView()

    }

    fun initView() {

        presenter = HomePresenter()
        presenter.init(this)
        presenter.connectToServer(getToken(this@HomeActivity))

        presenter.addFragment(FriendFragment())

        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }



    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {item ->
        when(item.itemId){
            R.id.freind -> {
                presenter.addFragment(FriendFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.chats -> {
                presenter.addFragment(ListChatFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.feeds -> {
                presenter.addFragment(FeedsFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
