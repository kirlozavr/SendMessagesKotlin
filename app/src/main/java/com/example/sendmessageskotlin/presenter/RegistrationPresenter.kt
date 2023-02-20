package com.example.sendmessageskotlin.presenter

import android.content.Context
import android.widget.Toast
import com.example.sendmessageskotlin.common.CallBackHandler
import com.example.sendmessageskotlin.common.Data
import com.example.sendmessageskotlin.common.StartActivityCallBack
import com.example.sendmessageskotlin.entity.UserEntity
import com.example.sendmessageskotlin.model.RegistrationModel

class RegistrationPresenter
constructor(context: Context, startActivityCallBack: StartActivityCallBack) {

    private val context: Context
    private var isRegistration: Boolean = false
    private var isSaveUser: Boolean = false
    private lateinit var userEntity: UserEntity
    private val startActivityCallBack: StartActivityCallBack
    private val registrationModel: RegistrationModel = RegistrationModel()
    private val getEntityCallBack = object : CallBackHandler<UserEntity> {
        override fun execute(value: UserEntity) {
            authorization(value)
        }
    }

    init {
        this.context = context
        this.startActivityCallBack = startActivityCallBack
    }

    fun setIsRegistration(isRegistration: Boolean):
            RegistrationPresenter {
        this.isRegistration = isRegistration
        return this
    }

    fun setIsSaveUser(isSaveUser: Boolean):
            RegistrationPresenter {
        this.isSaveUser = isSaveUser
        return this
    }

    fun setUserEntity(userEntity: UserEntity):
            RegistrationPresenter {
        this.userEntity = userEntity
        return this
    }

    fun signIn() {
        getEntityByName(
            getEntityCallBack,
            userEntity.getUsername()
        )
    }

    private fun getEntityByName(
        callBack: CallBackHandler<UserEntity>,
        userName: String
    ) {
        registrationModel
            .getEntityByName(callBack, userName)
    }

    private fun authorization(_userEntity: UserEntity) {
        var userEntityExists: Boolean = _userEntity.getUsername() != ""

        if (
            !isRegistration
            and userEntityExists
            and (userEntity.getPassword() == _userEntity.getPassword())
        ) {
            runStartActivity()
        }


        if (
            !isRegistration
            and (
                    !userEntityExists
                            or (userEntity.getPassword() != _userEntity.getPassword())
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
        if (isSaveUser) {
            Data.putStringPreferences(
                context,
                Data.SAVE_USERNAME,
                userEntity.getUsername()
            )
        }

        Data.putStringPreferences(
            context,
            Data.USERNAME,
            userEntity.getUsername()
        )

        startActivityCallBack.start()
    }

    private fun registrationUser() {
        registrationModel.postEntity(userEntity)
    }
}