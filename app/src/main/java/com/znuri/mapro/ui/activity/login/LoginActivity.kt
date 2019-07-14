package com.znuri.mapro.ui.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.znuri.mapro.R
import com.znuri.mapro.Utils.SaveSharedPrefrences
import com.znuri.mapro.Utils.setToken
import com.znuri.mapro.ui.activity.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login2.*

class LoginActivity : AppCompatActivity(), LoginView.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        init()

        btnLogin.setOnClickListener {
            signIn()
        }
    }

    lateinit var presenter: LoginPresenter
    lateinit var auth:FirebaseAuth
    lateinit var googleSignClient: GoogleSignInClient

    fun signIn() {
        val status = googleSignClient.silentSignIn()
        if(status.isSuccessful){
            presenter.login(status.result!!)
        } else {
            val sign = googleSignClient.signInIntent
            startActivityForResult(sign, RC_SIGN_IN)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                    presenter.login(account!!)
            } catch (e: ApiException) {
                Log.w(TAG, e.toString())
            }
        }
    }

    fun init() {
        presenter = LoginPresenter()
        presenter.init(this)

        auth = FirebaseAuth.getInstance()
        initGoogleService()

        presenter.checking()
    }

    fun initGoogleService(){
        val gson = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN!!)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignClient = GoogleSignIn.getClient(this,gson)
    }


    override fun onProgres() {

    }

    override fun onSuccess() {
        setToken(this@LoginActivity,auth.currentUser?.uid)
        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
    }

    override fun onFailed() {
        Toast.makeText(applicationContext,"Failed",Toast.LENGTH_LONG).show()
    }


    companion object {
        private const val TAG = "LoginActivity"
        private const val RC_SIGN_IN = 9001
    }
}
