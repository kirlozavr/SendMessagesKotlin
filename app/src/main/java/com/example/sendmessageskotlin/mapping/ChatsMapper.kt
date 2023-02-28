package com.example.sendmessageskotlin.mapping

import com.example.sendmessageskotlin.common.DateFormat
import com.example.sendmessageskotlin.dto.ChatsDto
import com.example.sendmessageskotlin.entity.ChatsEntity
import java.time.ZonedDateTime

class ChatsMapper: Mapping<ChatsEntity, ChatsDto> {

    private var isToday: Boolean = true

    override fun getEntityToDto(entity: ChatsEntity): ChatsDto {
        var zonedDateTime: ZonedDateTime = ZonedDateTime.parse(
            entity.getTimeMessageToDataBase(),
            DateFormat.getFormatFromDataBase()
        )

        return if(isToday){
            ChatsDto(
                entity.getUsernameToWhom(),
                entity.getLastMessage(),
                DateFormat.getFormatToDateAndTime().format(zonedDateTime)
            )
        } else{
            ChatsDto(
                entity.getUsernameToWhom(),
                entity.getLastMessage(),
                DateFormat.getFormatToTime().format(zonedDateTime)
            )
        }
    }

    fun setIsToday(isToday: Boolean){
        this.isToday = isToday
    }
}