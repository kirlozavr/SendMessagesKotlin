package com.example.sendmessageskotlin.contract

import com.example.sendmessageskotlin.common.CallBackHandler
import com.example.sendmessageskotlin.dto.SearchDto

interface SearchContract {
    interface View{

    }
    interface Model {
        fun getUsersByName(username: String, callBackHandler: CallBackHandler<List<SearchDto>>)
    }
    interface Presenter{

    }
}