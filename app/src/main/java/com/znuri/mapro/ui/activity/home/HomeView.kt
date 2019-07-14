package com.znuri.mapro.ui.activity.home

import com.sendbird.android.SendBirdException
import com.sendbird.android.User
import com.znuri.mapro.services.Fragment.BaseFragment

class HomeView {
    interface View {
        fun onSuccses()
        fun onFailed(message:SendBirdException?)
        fun onProgress()
        fun onFetched(mutableList: MutableList<User>?)
        fun setFragment(fragment: BaseFragment?)
    }

    interface Presenter<in T>{
        fun connectToServer(token: String?)
        fun getAllUser()

    }
}