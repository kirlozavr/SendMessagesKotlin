package com.example.sendmessageskotlin.presenter

import com.example.sendmessageskotlin.adapters.RecyclerViewCallBack
import com.example.sendmessageskotlin.common.CallBackHandler
import com.example.sendmessageskotlin.common.DateFormat
import com.example.sendmessageskotlin.dto.ChatsDto
import com.example.sendmessageskotlin.entity.ChatsEntity
import com.example.sendmessageskotlin.mapping.ChatsMapper
import com.example.sendmessageskotlin.model.ChatsModel
import java.time.LocalDate
import java.time.ZonedDateTime
import kotlin.streams.toList

class ChatsPresenter {

    private val mapper = ChatsMapper()
    private val model = ChatsModel()

    fun showChats(
        usernameFrom: String,
        recyclerViewCallBack: RecyclerViewCallBack<List<ChatsDto>>
    ) {
        val getChatsCallBack = object : CallBackHandler<List<ChatsEntity>> {
            override fun execute(value: List<ChatsEntity>) {
                recyclerViewCallBack.get(mappingChats(value))
            }
        }
        model.getAllChats(getChatsCallBack, usernameFrom)
    }

    private fun mappingChats(value: List<ChatsEntity>): List<ChatsDto> {
        return value
            .toMutableList()
            .stream()
            .map { chatEntity -> mapping(chatEntity) }
            .toList()
    }

    private fun mapping(chatEntity: ChatsEntity): ChatsDto {
        val zonedDateTime = ZonedDateTime.parse(
            chatEntity.getTimeMessageToDataBase(),
            DateFormat.getFormatFromDataBase()
        )
        if (zonedDateTime.toLocalDate().isEqual(LocalDate.now())) {
            mapper.setIsToday(false)
        } else {
            mapper.setIsToday(true)
        }
        return mapper.getEntityToDto(chatEntity)
    }
}