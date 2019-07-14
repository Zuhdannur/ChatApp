package com.znuri.mapro.ui.activity.addfreind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.sendbird.android.User
import com.tapadoo.alerter.Alerter
import com.znuri.mapro.R
import com.znuri.mapro.Utils.toGone
import com.znuri.mapro.Utils.toVisible
import com.znuri.mapro.services.Adapter.AllUserAdapter
import com.znuri.mapro.ui.activity.home.HomeActivity
import kotlinx.android.synthetic.main.activity_add_freind.*

class AddFreindActivity : AppCompatActivity(), AddFreindView.View ,AllUserAdapter.onRecyclerViewListener{

    override fun onClickItem(user: User) {
        Alerter.create(this@AddFreindActivity)
            .setTitle("Notice")
            .setText("Anda Yakin Menambahkan "+user.nickname+" Sebagain Teman ?")
            .setBackgroundColorInt(R.color.colorPrimary)
            .disableOutsideTouch()
            .enableInfiniteDuration(true)
            .addButton("Yaqueen",R.style.AlertButton, View.OnClickListener {
                presenter.AddUser(user?.userId!!)
                Alerter.hide()
            })
            .addButton("Tidak",R.style.AlertButton,View.OnClickListener {
                Alerter.hide()
            })
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_freind)
        initView()
    }

    lateinit var listener:AllUserAdapter.onRecyclerViewListener
    lateinit var adapter:AllUserAdapter
    lateinit var presenter: AddFreindPresenter

    fun initView() {
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        listener = this

        presenter = AddFreindPresenter()
        presenter.init(this)

        presenter.getAllUser()

        adapter = AllUserAdapter()
        adapter.initInterface(listener)
    }


    override fun onSuccess() {
        startActivity(Intent(this@AddFreindActivity, HomeActivity::class.java))
    }

    override fun onFailed() {
        Alerter.create(this@AddFreindActivity)
            .setTitle("Notice")
            .setText("Gagal Menambahkan Teman")
            .setBackgroundColorInt(R.color.colorPrimaryDark)
            .show()
    }

    override fun onProgress() {
        loading.toVisible()
    }

    override fun onFetched(mutableList: MutableList<User>?) {
        loading.toGone()
        if(mutableList?.size !== 0){
            rcUsers.toVisible()

            rcUsers.apply {
                val adapt = AllUserAdapter(applicationContext,mutableList)
                this.adapter = adapt
                this.layoutManager = LinearLayoutManager(applicationContext)
                adapt.initInterface(listener)
            }
        }
    }
}
