package com.znuri.mapro.ui.activity.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sendbird.android.BaseMessage
import com.znuri.mapro.R
import com.znuri.mapro.services.Adapter.MessageAdapter
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity(), ChatView.View {

    lateinit var presenter:ChatPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
    }

    fun init() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        presenter = ChatPresenter()
        presenter.init(this)

        val bundle = intent.extras
        if(bundle != null) presenter.loadPreviousMessage(bundle?.getString("url"))

        btnSendMessage.setOnClickListener {
            presenter.sendAMessage(edt_message.text.toString())
        }
    }

    override fun onSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFetchedPreviousMessage(message: List<BaseMessage>?) {

        if(message?.size != 0 ){
            rcMessage.apply {
                this.adapter = MessageAdapter(context,message)
                this.layoutManager = LinearLayoutManager(this@ChatActivity)
            }
        } else {

        }
    }

}
