package com.znuri.mapro.ui.activity.chat

import com.sendbird.android.BaseMessage

class ChatView {
    interface View {
        fun onSuccess()
        fun onFailed()
        fun onProgress()

        fun onFetchedPreviousMessage(message:List<BaseMessage>?)
    }
    interface Presenter<in T>{
        fun createChannel(urlChannel:String?)
        fun sendAMessage(message:String?)
    }
}