package com.example.sendmessageskotlin

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sendmessageskotlin.common.CallBackHandler
import com.example.sendmessageskotlin.entity.ChatsEntity
import com.example.sendmessageskotlin.model.ChatsModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChatsModelTest {

    @Test
    fun getAllChatsByName(){
        var chatsModel = ChatsModel()
        val callBack = object : CallBackHandler<List<ChatsEntity>> {
            override fun execute(value: List<ChatsEntity>) {
                Assert.assertTrue(value.isNotEmpty())
            }
        }
        chatsModel.getAllChats(callBack, "папа")
    }
}