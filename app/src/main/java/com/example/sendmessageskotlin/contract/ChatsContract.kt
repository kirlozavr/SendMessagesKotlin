package com.example.sendmessageskotlin.contract

import com.example.sendmessageskotlin.adapters.RecyclerViewCallBack
import com.example.sendmessageskotlin.common.CallBackHandler
import com.example.sendmessageskotlin.dto.ChatsDto
import com.example.sendmessageskotlin.entity.ChatsEntity

interface ChatsContract {
    interface View{

    }
    interface Model {
        fun getAllChats(callBackHandler: CallBackHandler<List<ChatsEntity>>, usernameFrom: String)
    }
    interface Presenter{
        fun showChats(usernameFrom: String, recyclerViewCallBack: RecyclerViewCallBack<List<ChatsDto>>)
    }
}