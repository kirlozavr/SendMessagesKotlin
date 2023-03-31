package com.example.sendmessageskotlin.repository

import com.example.sendmessageskotlin.common.DataBase
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ChatsRepository {

    private val database = Firebase.firestore

    fun getAllChats(usernameFrom: String): Task<QuerySnapshot> {
        return database
            .collection(DataBase.CHATS_DB)
            .document(usernameFrom)
            .collection(DataBase.ListTag.COLLECTIONS_CHATS_TAG)
            .get()
    }
}