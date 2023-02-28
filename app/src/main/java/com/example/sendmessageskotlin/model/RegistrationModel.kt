package com.example.sendmessageskotlin.model

import com.example.sendmessageskotlin.common.CallBackHandler
import com.example.sendmessageskotlin.common.DataBase
import com.example.sendmessageskotlin.entity.UserEntity
import com.example.sendmessageskotlin.presenter.RegistrationPresenter
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
                val userEntity = document.toObject<UserEntity>()
                callBack.execute(userEntity ?: UserEntity())
            }
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