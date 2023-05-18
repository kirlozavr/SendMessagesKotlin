package com.example.sendmessageskotlin.presenter

import android.content.Context
import android.widget.Toast
import com.example.sendmessageskotlin.common.CallBackHandler
import com.example.sendmessageskotlin.common.Data
import com.example.sendmessageskotlin.common.StartActivityCallBack
import com.example.sendmessageskotlin.contract.RegistrationContract
import com.example.sendmessageskotlin.entity.UserEntity
import com.example.sendmessageskotlin.model.RegistrationModel

class RegistrationPresenter
constructor(
    context: Context,
    contractView: RegistrationContract.View,
    startActivityCallBack: StartActivityCallBack
) : RegistrationContract.Presenter {

    private val context: Context
    private var isRegistration: Boolean = false
    private var isSaveUser: Boolean = false
    private lateinit var userEntity: UserEntity
    private val contractModel: RegistrationContract.Model
    private val contractView: RegistrationContract.View
    private val startActivityCallBack: StartActivityCallBack
    private val getEntityCallBack = object : CallBackHandler<UserEntity> {
        override fun execute(value: UserEntity) {
            authorization(value)
        }
    }

    init {
        this.context = context
        this.contractView = contractView
        this.startActivityCallBack = startActivityCallBack
        this.contractModel = RegistrationModel()
    }

    override fun setIsRegistration(isRegistration: Boolean):
            RegistrationPresenter {
        this.isRegistration = isRegistration
        return this
    }

    override fun setIsSaveUser(isSaveUser: Boolean):
            RegistrationPresenter {
        this.isSaveUser = isSaveUser
        return this
    }

    override fun setUserEntity(userEntity: UserEntity):
            RegistrationPresenter {
        this.userEntity = userEntity
        return this
    }

    override fun signIn() {
        getEntityByName(
            getEntityCallBack,
            userEntity.getUsername()
        )
    }

    private fun getEntityByName(
        callBack: CallBackHandler<UserEntity>,
        userName: String
    ) {
        contractModel
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
        contractModel.postEntity(userEntity)
    }
}