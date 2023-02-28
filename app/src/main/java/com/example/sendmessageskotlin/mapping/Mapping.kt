package com.example.sendmessageskotlin.mapping

interface Mapping<E, D> {

    fun getEntityToDto(entity: E):D
}