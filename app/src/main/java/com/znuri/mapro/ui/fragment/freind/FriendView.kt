package com.znuri.mapro.ui.fragment.freind

import com.sendbird.android.User

class FriendView {
    interface View {
        fun onSuccess()
        fun onProgres()
        fun onFailure()

        fun onFetched(mutableList: MutableList<User>?)
    }
    interface Presenter<in T>{
        fun getMyFreind()
        fun unfreind(userId:String?)

        fun openChannel(userId: User?)
    }
}