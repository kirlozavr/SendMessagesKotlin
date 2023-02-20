package com.example.sendmessageskotlin.common

import java.time.format.DateTimeFormatter
import java.util.*

object DateFormat {

    fun getFormatToDataBase(): DateTimeFormatter{
        return DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss.SSS OOOO", Locale.ENGLISH)
    }

    fun getFormatFromDataBase(): DateTimeFormatter{
        return DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss.SSS zzzz", Locale.ENGLISH)
    }

    fun getFormatToDateAndTime(): DateTimeFormatter{
        return DateTimeFormatter
            .ofPattern("dd-MM-yyyy HH:mm", Locale.ENGLISH)
    }

    fun getFormatToTime(): DateTimeFormatter{
        return DateTimeFormatter
            .ofPattern("HH:mm", Locale.ENGLISH)
    }
}