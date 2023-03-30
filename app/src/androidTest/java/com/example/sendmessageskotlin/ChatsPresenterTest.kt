package com.example.sendmessageskotlin

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sendmessageskotlin.adapters.RecyclerViewCallBack
import com.example.sendmessageskotlin.dto.ChatsDto
import com.example.sendmessageskotlin.presenter.ChatsPresenter
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChatsPresenterTest {

    private lateinit var chatsPresenter: ChatsPresenter

    @Before
    fun startUp(){
        chatsPresenter = ChatsPresenter()
    }

    @Test
    fun showChatsTestIsCorrect(){
        val recyclerViewCallBack = object : RecyclerViewCallBack<List<ChatsDto>> {
            override fun get(value: List<ChatsDto>) {
                Assert.assertTrue(value.isNotEmpty())
            }

        }
        chatsPresenter.showChats("папа", recyclerViewCallBack)
    }

    @Test
    fun showChatsTestIsNotCorrect(){
        val recyclerViewCallBack = object : RecyclerViewCallBack<List<ChatsDto>> {
            override fun get(value: List<ChatsDto>) {
                Assert.assertTrue(value.isEmpty())
            }

        }
        chatsPresenter.showChats("папа1", recyclerViewCallBack)
    }
}