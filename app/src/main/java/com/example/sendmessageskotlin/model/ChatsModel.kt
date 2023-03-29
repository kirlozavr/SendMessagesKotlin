package com.example.sendmessageskotlin.model

import com.example.sendmessageskotlin.common.CallBackHandler
import com.example.sendmessageskotlin.common.DataBase
import com.example.sendmessageskotlin.entity.ChatsEntity
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ChatsModel {

    private val database = Firebase.firestore

    fun getAllChats(
        callBackHandler: CallBackHandler<List<ChatsEntity>>,
        usernameFrom: String
    ){
        database
            .collection(DataBase.CHATS_DB)
            .document(usernameFrom)
            .collection(DataBase.ListTag.COLLECTIONS_CHATS_TAG)
            .get()
            .addOnSuccessListener{ result ->
                var chatsList = result
                    .map { document -> document.toObject<ChatsEntity>() }
                    .toList()
                callBackHandler.execute(chatsList)
            }
    }
}