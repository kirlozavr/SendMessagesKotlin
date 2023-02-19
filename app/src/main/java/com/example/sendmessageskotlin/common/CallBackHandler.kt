package com.example.sendmessageskotlin.common

interface CallBackHandler <V>{
    fun execute(value: V)
}