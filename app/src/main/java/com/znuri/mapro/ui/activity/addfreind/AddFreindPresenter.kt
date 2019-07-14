package com.znuri.mapro.ui.activity.addfreind

import com.sendbird.android.SendBird
import com.sendbird.android.SendBirdException
import com.sendbird.android.User

class AddFreindPresenter : AddFreindView.Presenter<AddFreindView.View> {



    override fun AddUser(userId: String?) {
        var data: ArrayList<String> = ArrayList()
        data.add(userId.toString())
        SendBird.addFriends(data,object : SendBird.AddFriendsHandler{
            override fun onResult(p0: MutableList<User>?, p1: SendBirdException?) {
                if(p1 != null) view?.onFailed()
                else view?.onSuccess()
            }
        })


    }

    override fun getAllUser() {
        var applicationUserListQuery = SendBird.createApplicationUserListQuery()
        applicationUserListQuery.next { p0, p1 ->
            if(p1 === null){
                view?.onFetched(p0)
            }
        }
    }


    lateinit var view:AddFreindView.View

    fun init(view:AddFreindView.View){
        this.view = view
    }
}