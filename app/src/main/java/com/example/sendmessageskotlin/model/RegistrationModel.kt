package com.example.sendmessageskotlin.model

import com.example.sendmessageskotlin.common.CallBackHandler
import com.example.sendmessageskotlin.common.DataBase
import com.example.sendmessageskotlin.entity.UserEntity
import com.example.sendmessageskotlin.exception.ErrorRequestException
import com.example.sendmessageskotlin.exception.UserNotFoundException
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class RegistrationModel {

    private var dataBase = Firebase.firestore

    fun getEntityByName(
        callBack: CallBackHandler<UserEntity>,
        userName: String
    ) {
        dataBase
            .collection(DataBase.NAME_DB)
            .document(userName)
            .get()
            .addOnSuccessListener { document ->
                if(!document.exists()){
                    throw UserNotFoundException("Пользователь не найден.")
                }
                val userEntity = document.toObject<UserEntity>()
                callBack.execute(userEntity ?: UserEntity())
            }.addOnFailureListener(
                OnFailureListener { exception ->
                    throw ErrorRequestException(exception.message.toString())
                }
            )
    }

    fun postEntity(userEntity: UserEntity) {
        dataBase
            .collection(DataBase.NAME_DB)
            .document(userEntity.getUsername())
            .set(userEntity)
    }

    fun putEntity() {

    }

    fun deleteEntity() {

    }
}