package com.example.sendmessageskotlin.common

object DataBase {
    /**
     * Название таблицы с пользователями
     */
    const val NAME_DB = "User"

    /**
     * Название таблицы с сообщениями
     */
    const val MESSAGES_DB = "Messages"

    /**
     * Название таблицы с чатами
     */
    const val CHATS_DB = "Chats"

    /**
     * Класс отвечает за хранение констант - ключей для обращения к спискам значений в таблице
     */
    object ListTag {
        /**
         * Название списка с чатами
         */
        const val COLLECTIONS_CHATS_TAG = "ListChats"

        /**
         * Название списка с сообщениями
         */
        const val COLLECTIONS_MESSAGES_TAG = "ListMessages"
    }
}

