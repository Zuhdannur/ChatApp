package com.znuri.mapro.ui.activity.login

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser

open class LoginView {
    interface View {

        fun onProgres()
        fun onSuccess()
        fun onFailed()

    }

    interface Presenter<in T>{
        fun login(acct: GoogleSignInAccount)
        fun checking()
    }
}