package com.example.sendmessageskotlin.dto

class ChatsDto(
    usernameToWhom: String,
    lastMessage: String,
    timeMessageToChats: String
    ) {

    private lateinit var usernameToWhom: String
    private lateinit var lastMessage: String
    private lateinit var timeMessageToChats: String

    init {
        this.usernameToWhom = usernameToWhom
        this.lastMessage = lastMessage
        this.timeMessageToChats = timeMessageToChats
    }

    fun getUsernameToWhom(): String{
        return usernameToWhom
    }

    fun getLastMessage(): String{
        return lastMessage
    }

    fun getTimeMessageToChats(): String{
        return timeMessageToChats
    }
}