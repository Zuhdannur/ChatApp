package com.znuri.mapro.ui.fragment.listchat

import com.sendbird.android.*

class ListChatPresenter : ListChatView.Presenter<ListChatView.View> {

    lateinit var view : ListChatView.View

    fun init(view : ListChatView.View) {
        this.view = view
    }

    override fun getMyChannel() {
        var query = GroupChannel.createMyGroupChannelListQuery()
        query.isIncludeEmpty = true
        query.next(object : GroupChannelListQuery.GroupChannelListQueryResultHandler{
            override fun onResult(p0: MutableList<GroupChannel>?, p1: SendBirdException?) {
                if(p1 != null )view?.onFailed()
                else view.onFetched(p0)
            }

        })
    }
}