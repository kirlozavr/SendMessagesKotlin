package com.example.sendmessageskotlin.mapping

import com.example.sendmessageskotlin.dto.SearchDto
import com.example.sendmessageskotlin.entity.UserEntity

class SearchMapper {

    fun getEntityToDto(entity: UserEntity): SearchDto = SearchDto(entity.getUsername())
}