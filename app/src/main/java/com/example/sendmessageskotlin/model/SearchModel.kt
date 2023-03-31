package com.example.sendmessageskotlin.model

import com.example.sendmessageskotlin.common.CallBackHandler
import com.example.sendmessageskotlin.dto.SearchDto
import com.example.sendmessageskotlin.entity.UserEntity
import com.example.sendmessageskotlin.exception.ErrorRequestException
import com.example.sendmessageskotlin.exception.UserNotFoundException
import com.example.sendmessageskotlin.mapping.SearchMapper
import com.example.sendmessageskotlin.repository.SearchRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.firestore.ktx.toObject

class SearchModel {

    private val repository = SearchRepository()
    private val mapper = SearchMapper()

    fun getUsersByName(
        username: String,
        callBackHandler: CallBackHandler<List<SearchDto>>
    ) {
        repository
            .getUsersByName(username)
            .addOnCompleteListener(OnCompleteListener { query ->
                if (query.result.isEmpty) {
                    throw UserNotFoundException("Такой пользователь не найден.")
                }
                callBackHandler.execute(
                    query
                        .result
                        .map { result -> mapper.getEntityToDto(result.toObject<UserEntity>()) }
                        .toList()
                )
            }).addOnFailureListener(OnFailureListener { exception ->
                throw ErrorRequestException(exception.message.toString())
            })
    }
}