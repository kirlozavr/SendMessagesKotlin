package com.example.sendmessageskotlin.repository

import com.example.sendmessageskotlin.common.DataBase
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SearchRepository {

    private val database = Firebase.firestore

    fun getUsersByName(username: String): Task<QuerySnapshot> {
        return database
            .collection(DataBase.NAME_DB)
            .whereGreaterThanOrEqualTo("username", username)
            .get()
    }
}