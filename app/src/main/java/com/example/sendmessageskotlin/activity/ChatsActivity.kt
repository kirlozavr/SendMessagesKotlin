package com.example.sendmessageskotlin.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sendmessageskotlin.R
import com.example.sendmessageskotlin.adapters.RecyclerViewAdapterChats
import com.example.sendmessageskotlin.adapters.RecyclerViewCallBack
import com.example.sendmessageskotlin.common.Data
import com.example.sendmessageskotlin.common.OnClickListener
import com.example.sendmessageskotlin.dto.ChatsDto
import com.example.sendmessageskotlin.presenter.ChatsPresenter
import com.example.sendmessageskotlin.service.NetworkIsConnectedService

class ChatsActivity : AppCompatActivity() {

    private val USERNAME_FROM = "usernameFromChats"

    private lateinit var toolbar: Toolbar
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterChats: RecyclerViewAdapterChats
    private lateinit var usernameFrom: String
    private val chatsPresenter = ChatsPresenter()
    private val getChatsCallBack = object : RecyclerViewCallBack<List<ChatsDto>> {
        override fun get(value: List<ChatsDto>) {
            adapterChats.deleteList()
            adapterChats.setList(value.toMutableList())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats)
        init()
        isConnected()
        chatsPresenter.showChats(usernameFrom, getChatsCallBack)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(
            USERNAME_FROM,
            usernameFrom
        )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        usernameFrom = savedInstanceState
            .getString(USERNAME_FROM)
            .toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.search_user -> run {
                val intent: Intent = Intent(
                    this@ChatsActivity,
                    SearchActivity::class.java
                )
                startActivity(intent)
            }

            R.id.exit -> kotlin.run {
                Data.removePreferences(this, Data.USERNAME)
                Data.removePreferences(this, Data.SAVE_USERNAME)
                val intent: Intent = Intent(
                    this@ChatsActivity,
                    RegistrationActivity::class.java
                )
                startActivity(intent)
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun init() {

        initView()

        usernameFrom = Data.getStringPreferences(this, Data.USERNAME)

        if (usernameFrom.isNotEmpty()) {
            supportActionBar?.title =
                "Добро пожаловать " +
                        usernameFrom.substring(0, 1).uppercase() +
                        usernameFrom.substring(1)
        }

        initRecycler()

    }

    private fun initView() {
        constraintLayout = findViewById(R.id.constraintLayoutChatsActivity)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun initRecycler() {
        val onClickListener: OnClickListener<ChatsDto> =
            object : OnClickListener<ChatsDto> {
                override fun onClick(entity: ChatsDto, position: Int) {
                    runStartActivity(entity.getUsernameToWhom())
                }
            }

        recyclerView = findViewById(R.id.recycleViewChats)
        adapterChats = RecyclerViewAdapterChats(
            this@ChatsActivity,
            onClickListener
        )
        val layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapterChats
    }

    private fun isConnected() {
        val networkIsConnectedService: NetworkIsConnectedService =
            ViewModelProvider(this)[NetworkIsConnectedService::class.java]
        networkIsConnectedService.isConnected(
            networkIsConnectedService,
            this,
            constraintLayout
        )
    }

    private fun runStartActivity(usernameToWhom: String) {
        val intent: Intent = Intent(
            this@ChatsActivity,
            MessagesSendActivity::class.java
        )
        intent.putExtra("usernameToWhom", usernameToWhom)
        startActivity(intent)
    }

}