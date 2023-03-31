package com.example.sendmessageskotlin.repository

import com.example.sendmessageskotlin.common.DataBase
import com.example.sendmessageskotlin.entity.UserEntity
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistrationRepository {

    private val database = Firebase.firestore

    fun getEntityByName(username: String): Task<DocumentSnapshot> {
        return database
            .collection(DataBase.NAME_DB)
            .document(username)
            .get()
    }

    fun postEntity(userEntity: UserEntity): Task<Void> {
        return database
            .collection(DataBase.NAME_DB)
            .document(userEntity.getUsername())
            .set(userEntity)
    }
}