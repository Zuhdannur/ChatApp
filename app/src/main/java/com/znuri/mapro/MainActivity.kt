package com.znuri.mapro

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.sendbird.android.SendBird
import com.znuri.mapro.Utils.getToken
import com.znuri.mapro.Utils.toVisible
import com.znuri.mapro.ui.activity.home.HomeActivity
import com.znuri.mapro.ui.activity.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val APP_ID = "A728396A-F8A8-4BA1-87BC-883E8EB1DEB2"
    val TIMEOUT:Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        Handler().postDelayed({
            if(isNetwork()){
                val auth = getToken(this@MainActivity)
                if(auth !== "") {
                    startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                } else {
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                }
                finish()
            } else {

                awan.toVisible()
                ket.toVisible()
                reconect.toVisible()

            }
        },TIMEOUT)

        reconect.setOnClickListener {
            if(isNetwork()){
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
        }
    }

    fun init() {
        SendBird.init(APP_ID,applicationContext)
    }

    @SuppressLint("ServiceCast")
    fun isNetwork() : Boolean{
        val connect: ConnectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connect.activeNetworkInfo != null && connect.activeNetworkInfo.isConnected
    }
}
