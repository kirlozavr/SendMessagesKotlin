package com.example.sendmessageskotlin.model

import com.example.sendmessageskotlin.common.CallBackHandler
import com.example.sendmessageskotlin.common.DataBase
import com.example.sendmessageskotlin.entity.ChatsEntity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ChatsModel {

    private var database = Firebase.firestore

    fun getAllChats(
        callBackHandler: CallBackHandler<ChatsEntity>,
        usernameFrom: String
    ){
        database
            .collection(DataBase.CHATS_DB)
            .document(usernameFrom)
            .collection(DataBase.ListTag.COLLECTIONS_CHATS_TAG)
            .addSnapshotListener()
    }
}