package com.znuri.mapro.ui.fragment.listchat

import com.sendbird.android.GroupChannel
import com.sendbird.android.OpenChannel

class ListChatView  {
    interface View {
        fun onSuccess()
        fun onFailed()
        fun onProgress()

        fun onFetched(data:MutableList<GroupChannel>?)
    }
    interface Presenter<in T>{
        fun getMyChannel()

    }
}