package com.example.sendmessageskotlin.common

interface OnClickListener<E> {

    fun onClick(entity: E, position: Int)
}