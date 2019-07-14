package com.znuri.mapro.ui.activity.login

import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginPresenter :
    LoginView.Presenter<LoginView.View> {

    override fun checking() {
        val users = auth.currentUser
        if(users?.displayName !== null){
            view.onSuccess()
        }
    }


    lateinit var view: LoginView.View
    lateinit var auth: FirebaseAuth

    fun init(view: LoginView.View) {
        this.view = view
        auth = FirebaseAuth.getInstance()
    }


    override fun login(acct: GoogleSignInAccount) {
        Log.d("LoginActivity","Firebase AuthWith Google "+acct.id!!)
        val credential = GoogleAuthProvider.getCredential(acct.idToken,null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                task ->
                if(task.isSuccessful) {
                    view.onSuccess()
                } else {
                    view.onFailed()
                }
            }
    }
}