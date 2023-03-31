package com.example.sendmessageskotlin.model

import com.example.sendmessageskotlin.common.CallBackHandler
import com.example.sendmessageskotlin.entity.ChatsEntity
import com.example.sendmessageskotlin.exception.ChatsNotFoundException
import com.example.sendmessageskotlin.exception.ErrorRequestException
import com.example.sendmessageskotlin.repository.ChatsRepository
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.firestore.ktx.toObject

class ChatsModel {

    private val repository = ChatsRepository()

    fun getAllChats(
        callBackHandler: CallBackHandler<List<ChatsEntity>>,
        usernameFrom: String
    ) {
        repository
            .getAllChats(usernameFrom)
            .addOnSuccessListener { result ->
                if (result.isEmpty) {
                    throw ChatsNotFoundException("Чаты этого пользователя не найдены.")
                }
                var chatsList = result
                    .map { document -> document.toObject<ChatsEntity>() }
                    .toList()
                callBackHandler.execute(chatsList)
            }.addOnFailureListener(OnFailureListener { exception ->
                throw ErrorRequestException(exception.message.toString())
            })
    }
}