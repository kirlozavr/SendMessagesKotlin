package com.example.sendmessageskotlin.entity

import android.widget.Toast

class UserEntity() {

    private var id: Int = 0
    private lateinit var username: String
    private lateinit var password: String

    constructor(
        username: String,
        password: String
    ) : this() {
        this.username = username
        this.password = password
    }

    constructor(
        id: Int,
        username: String,
        password: String
    ): this(username, password){
        this.id = id
    }

    fun getUsername(): String = username
    fun getPassword(): String = password
}
