package com.znuri.mapro

import android.app.Application
import com.sendbird.android.SendBird

open class MaPro : Application() {

    override fun onCreate() {
        super.onCreate()
        SendBird.init(APP_ID,baseContext)
    }

    companion object {
        val APP_ID = "A728396A-F8A8-4BA1-87BC-883E8EB1DEB2"
    }
}