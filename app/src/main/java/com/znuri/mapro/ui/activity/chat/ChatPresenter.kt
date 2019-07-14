package com.znuri.mapro.ui.activity.chat

import com.sendbird.android.*

class ChatPresenter : ChatView.Presenter<ChatView.View> {

    override fun sendAMessage(message: String?) {
        var params : UserMessageParams = UserMessageParams()
            .setMessage(message)
            .setMentionType(BaseMessageParams.MentionType.CHANNEL)
            .setPushNotificationDeliveryOption(BaseMessageParams.PushNotificationDeliveryOption.DEFAULT)
             group.sendUserMessage(params,BaseChannel.SendUserMessageHandler { userMessage, sendBirdException ->
            if(sendBirdException != null) return@SendUserMessageHandler

        })
    }

    override fun createChannel(urlChannel: String?) {
        GroupChannel.getChannel(urlChannel,GroupChannel.GroupChannelGetHandler { groupChannel, sendBirdException ->
            if(sendBirdException != null) return@GroupChannelGetHandler
//            else loadPreviousMessage(groupChannel)
        })
    }
    lateinit private var group:GroupChannel
    fun loadPreviousMessage(url:String?) {
        GroupChannel.getChannel(url,GroupChannel.GroupChannelGetHandler { groupChannel,
                                                                          sendBirdException ->
            if(sendBirdException != null) return@GroupChannelGetHandler
            this.group = groupChannel
            var previosMessage:PreviousMessageListQuery = groupChannel?.createPreviousMessageListQuery()!!
            previosMessage.load(30,true)
            {
                    p0, p1 ->
                if(p1 != null)return@load
                else view?.onFetchedPreviousMessage(p0)
            }
        })



    }
    lateinit var view:ChatView.View
    fun init(view: ChatView.View){
        this.view = view
    }


}