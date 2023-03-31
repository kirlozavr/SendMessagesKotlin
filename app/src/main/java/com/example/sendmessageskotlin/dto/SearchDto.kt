package com.example.sendmessageskotlin.dto

class SearchDto constructor(username: String) {

    private var username: String

    init{
        this.username = username
    }

    fun getUsername(): String = username
}