package com.znuri.mapro.ui.activity.addfreind

import com.sendbird.android.User

class AddFreindView {
    interface View {
        fun onSuccess()
        fun onFailed()
        fun onProgress()

        fun onFetched(mutableList: MutableList<User>?)
    }
    interface Presenter<in T>{
        fun AddUser(userId:String?)
        fun getAllUser()
    }
}