package com.example.sendmessageskotlin.presenter

import android.content.Context
import com.example.sendmessageskotlin.common.CallBackHandler
import com.example.sendmessageskotlin.common.StartActivityCallBack
import com.example.sendmessageskotlin.entity.ChatsEntity
import com.example.sendmessageskotlin.mapping.ChatsMapper

class ChatsPresenter
constructor(context: Context, startActivityCallBack: StartActivityCallBack){

    private val USERNAME_FROM = "usernameFromChats"

    private var isToday = true
    private val context: Context
    private lateinit var usernameFrom: String
    private val mapper = ChatsMapper()
    private val startActivityCallBack: StartActivityCallBack
    private val getChatsCallBack = object : CallBackHandler<ChatsEntity>{
        override fun execute(value: ChatsEntity) {

        }
    }

    init {
        this.context = context
        this.startActivityCallBack = startActivityCallBack
    }

    fun showChats(usernameFrom: String){

    }
}