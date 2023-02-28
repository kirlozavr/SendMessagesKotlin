package com.example.sendmessageskotlin.entity

import kotlin.math.roundToInt

class ChatsEntity() {

    private var idChats: Int = 0
    private lateinit var usernameToWhom: String
    private lateinit var lastMessage: String
    private lateinit var timeMessageToDataBase: String

    constructor(usernameToWhom: String) : this() {
        this.idChats = (Math.random() * 1000000).roundToInt()
        this.usernameToWhom = usernameToWhom
    }

    constructor(idChats: Int, usernameToWhom: String): this(){
        this.idChats = idChats
        this.usernameToWhom = usernameToWhom
    }

    constructor(
        idChats: Int,
        usernameToWhom: String,
        lastMessage: String,
        timeMessageToDataBase: String
    ) : this(){
        this.idChats = idChats
        this.usernameToWhom = usernameToWhom
        this.lastMessage = lastMessage
        this.timeMessageToDataBase = timeMessageToDataBase
    }

    fun getIdChats(): Int{
        return idChats
    }

    fun getLastMessage(): String{
        return  lastMessage
    }

    fun getUsernameToWhom(): String{
        return usernameToWhom
    }

    fun getTimeMessageToDataBase(): String{
        return timeMessageToDataBase
    }
}