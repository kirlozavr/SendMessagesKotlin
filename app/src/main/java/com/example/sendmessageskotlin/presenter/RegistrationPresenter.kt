package com.example.sendmessageskotlin.presenter

import android.content.Context
import android.widget.Toast
import com.example.sendmessageskotlin.common.CallBackHandler
import com.example.sendmessageskotlin.entity.UserEntity
import com.example.sendmessageskotlin.model.RegistrationModel

class RegistrationPresenter constructor(context: Context) {

    private val context: Context
    private var isRegistration: Boolean = false
    private lateinit var userPassword: String
    private lateinit var userEntity: UserEntity
    private val registrationModel: RegistrationModel = RegistrationModel()
    private val getEntityCallBack = object : CallBackHandler<UserEntity> {
        override fun execute(value: UserEntity) {
            authorization(value)
        }
    }

    init {
        this.context = context
    }

    fun setIsRegistration(isRegistration: Boolean):
            RegistrationPresenter {
        this.isRegistration = isRegistration
        return this
    }

    fun setUserEntity(userEntity: UserEntity):
            RegistrationPresenter {
        this.userEntity = userEntity
        return this
    }

    fun setUserPassword(userPassword: String):
            RegistrationPresenter {
        this.userPassword = userPassword
        return this
    }

    fun signIn(userName: String) {
        getEntityByName(getEntityCallBack, userName)
    }

    private fun getEntityByName(
        callBack: CallBackHandler<UserEntity>,
        userName: String
    ) {
        registrationModel.getEntityByName(callBack, userName)
    }

    private fun authorization(_userEntity: UserEntity) {
        var userEntityExists: Boolean = _userEntity.getUsername() != ""

        if (
            !isRegistration
            and userEntityExists
            and (userPassword == _userEntity.getPassword())
        ) {
            runStartActivity()
        }


        if (
            !isRegistration
            and (
                    !userEntityExists
                            or (userPassword != _userEntity.getPassword())
                    )
        ) {
            Toast.makeText(
                context,
                "Неверный логин или пароль",
                Toast.LENGTH_LONG
            ).show()
        }

        if (
            isRegistration
            and !userEntityExists
        ) {
            registrationUser()
            runStartActivity()
        }

        if (
            isRegistration
            and userEntityExists
        ) {
            Toast.makeText(
                context,
                "Пользователь с таким именем уже есть",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun runStartActivity() {
        
    }

    private fun registrationUser() {

    }
}