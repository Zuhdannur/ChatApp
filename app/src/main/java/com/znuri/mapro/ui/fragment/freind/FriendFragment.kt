package com.znuri.mapro.ui.fragment.freind


import android.app.AlertDialog
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.sendbird.android.User
import com.squareup.picasso.Picasso

import com.znuri.mapro.R
import com.znuri.mapro.Utils.setToken
import com.znuri.mapro.Utils.toGone
import com.znuri.mapro.Utils.toVisible
import com.znuri.mapro.services.Fragment.BaseFragment
import com.znuri.mapro.services.Adapter.UserAdapter
import com.znuri.mapro.ui.activity.addfreind.AddFreindActivity
import com.znuri.mapro.ui.activity.home.HomeActivity
import com.znuri.mapro.ui.activity.login.LoginActivity
import kotlinx.android.synthetic.main.dialog_user_info.view.*
import kotlinx.android.synthetic.main.fragment_friend.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FriendFragment : BaseFragment(), FriendView.View , UserAdapter.OnRecyclerViewListener{

    override fun onClickItem(user: User?) {
        makeDialog(R.layout.dialog_user_info,user)
    }

    lateinit var views: View
    lateinit var presenter:FriendPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        views = inflater.inflate(R.layout.fragment_friend, container, false)
        initView()
        return views
    }

    lateinit var adapters:UserAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var listeners:UserAdapter.OnRecyclerViewListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    fun makeDialog(view:Int,user:User?){
        val mDialog =LayoutInflater.from(context).inflate(view,null)
        Picasso.get().load(user?.profileUrl).into(mDialog.picProfile)
        mDialog.playersName.text = user?.nickname
        val builder = AlertDialog.Builder(context)
            .setView(mDialog)
        val mAlert = builder.show()

        mDialog.unfriend.setOnClickListener {
            presenter.unfreind(user?.userId)
            mAlert.dismiss()
        }
        mDialog.chat.setOnClickListener {
            presenter.openChannel(user!!)
            mAlert.dismiss()
        }
    }

    fun initView() {
        val activity = (activity as HomeActivity)
        activity.setSupportActionBar(views.toolbar)
        activity.supportActionBar?.setDisplayShowTitleEnabled(false)
        presenter = FriendPresenter()
        presenter.init(this)
        listeners = this

        adapters = UserAdapter()
        recyclerView = views.rcUsers

        presenter.getMyFreind()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu,menu)

        menu?.findItem(R.id.logout)?.icon = setBandge(menu?.findItem(R.id.logout)?.icon)
        menu?.findItem(R.id.addFriend)?.icon = setBandge(menu?.findItem(R.id.addFriend)?.icon)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.logout -> {
                FirebaseAuth.getInstance().signOut()
                setToken(context!!,"")
                startActivity(Intent(activity?.applicationContext, LoginActivity::class.java))
                activity?.finish()
                return true
            }
            R.id.addFriend -> {
                startActivity(Intent(activity?.applicationContext, AddFreindActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setBandge(drawable: Drawable?) : Drawable {
        var drawable = drawable
        drawable = DrawableCompat.wrap(drawable!!)
        DrawableCompat.setTint(drawable, ContextCompat.getColor(activity!!.applicationContext,R.color.colorBlack))
        return drawable
    }

    override fun onSuccess() {
        adapters.notifyDataSetChanged()
        views.loading.toGone()
    }

    override fun onProgres() {
        views.loading.toVisible()
    }

    override fun onFailure() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFetched(mutableList: MutableList<User>?) {
        views.loading.toGone()
        if(mutableList?.size !== 0){
            recyclerView.toVisible()
            recyclerView.apply {
                adapters = UserAdapter(activity?.applicationContext,mutableList)
                recyclerView.adapter = adapters
                recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
                adapters.init(listeners)
            }
        } else {
            views.view_no_freind.toVisible()
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_friend
    }

}
