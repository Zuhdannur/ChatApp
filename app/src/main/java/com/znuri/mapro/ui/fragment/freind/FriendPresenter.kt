package com.znuri.mapro.ui.fragment.freind

import com.google.firebase.auth.FirebaseAuth
import com.sendbird.android.*

class FriendPresenter : FriendView.Presenter<FriendView.View> {
    override fun openChannel(userId: User?) {

        var user:ArrayList<String> = ArrayList()
        user.add(userId!!.userId)
        user.add(FirebaseAuth.getInstance().currentUser!!.uid)

        GroupChannel.createChannelWithUserIds(user,true,object : GroupChannel.GroupChannelCreateHandler{
            override fun onResult(p0: GroupChannel?, p1: SendBirdException?) {
                if(p1 != null) return
            }
        })
    }

    override fun unfreind(userId: String?) {
        view?.onProgres()
        var users: ArrayList<String> = ArrayList()
        users.add(userId!!)

        SendBird.deleteFriend(userId, object : SendBird.DeleteFriendHandler {
            override fun onResult(p0: SendBirdException?) {
                if (p0 != null) return
                else view?.onSuccess()
            }

        })

    }

    lateinit var view: FriendView.View

    fun init(view: FriendView.View) {
        this.view = view
    }

    override fun getMyFreind() {
        view?.onProgres()
        var applicationUserListQuery = SendBird.createFriendListQuery()
        applicationUserListQuery.next { p0, p1 ->
            if (p1 === null) {
                view?.onFetched(p0)
            }
        }
    }
}