package com.example.sendmessageskotlin.contract

import com.example.sendmessageskotlin.common.CallBackHandler
import com.example.sendmessageskotlin.entity.UserEntity
import com.example.sendmessageskotlin.presenter.RegistrationPresenter

interface RegistrationContract {
    interface View{

    }
    interface Model {
        fun getEntityByName(callBack: CallBackHandler<UserEntity>, username: String)
        fun postEntity(userEntity: UserEntity)
    }
    interface Presenter{
        fun setIsRegistration(isRegistration: Boolean): RegistrationPresenter
        fun setIsSaveUser(isSaveUser: Boolean): RegistrationPresenter
        fun setUserEntity(userEntity: UserEntity): RegistrationPresenter
        fun signIn()
    }
}