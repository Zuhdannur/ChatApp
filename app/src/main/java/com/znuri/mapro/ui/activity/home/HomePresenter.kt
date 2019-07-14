package com.znuri.mapro.ui.activity.home

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.sendbird.android.*
import com.znuri.mapro.services.Fragment.BaseFragment
import com.znuri.mapro.services.Fragment.FragmentNavigation

class HomePresenter : HomeView.Presenter<HomeView.View>,FragmentNavigation.Presenter {



    override fun addFragment(fragment: BaseFragment) {
            view?.setFragment(fragment)
    }

//    fun updateProfile(profielUrl:String?) {
//        val user = auth.currentUser
//        SendBird.updateCurrentUserInfo(user?.displayName,profielUrl,object : SendBird.UserInfoUpdateHandler{
//            override fun onUpdated(p0: SendBirdException?) {
//                if(p0 != null) return
//            }
//
//        })
//
//    }

    override fun getAllUser() {
        var applicationUserListQuery = SendBird.createFriendListQuery()
        applicationUserListQuery.next { p0, p1 ->
            if(p1 === null){
                view?.onFetched(p0)
            }
        }
    }

    lateinit var view: HomeView.View
//    lateinit var auth:FirebaseAuth

    fun init(view: HomeView.View) {
        this.view = view
//        auth = FirebaseAuth.getInstance()
    }

    override fun connectToServer(token: String?) {
        Log.d("HomePresenter",token)
        view?.onProgress()
        SendBird.connect(token, object : SendBird.ConnectHandler {
            override fun onConnected(p0: User?, p1: SendBirdException?) {
                if(p1 != null){
                    view?.onFailed(p1)
                    return
                } else {
//                    if(p0?.nickname == ""){ updateProfile(p0?.profileUrl) }
                    return
                }
            }

        })
    }


}