package com.example.sendmessageskotlin.entity

class UserEntity() {

    private var id: Int = 0
    private lateinit var username: String
    private lateinit var password: String

    constructor(
        id: Int,
        username: String,
        password: String
    ) : this() {
        this.id = id
        this.username = username
        this.password = password
    }

    fun getUsername(): String = username
    fun getPassword(): String = password
}
