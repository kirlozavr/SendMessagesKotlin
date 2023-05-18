package com.example.sendmessageskotlin.model

import com.example.sendmessageskotlin.common.CallBackHandler
import com.example.sendmessageskotlin.contract.RegistrationContract
import com.example.sendmessageskotlin.entity.UserEntity
import com.example.sendmessageskotlin.exception.ErrorRequestException
import com.example.sendmessageskotlin.exception.UserNotFoundException
import com.example.sendmessageskotlin.repository.RegistrationRepository
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.firestore.ktx.toObject

class RegistrationModel : RegistrationContract.Model{

    private val repository = RegistrationRepository()

    override fun getEntityByName(
        callBack: CallBackHandler<UserEntity>,
        username: String
    ) {
        repository
            .getEntityByName(username)
            .addOnSuccessListener { document ->
                if (!document.exists()) {
                    throw UserNotFoundException("Пользователь не найден.")
                }
                val userEntity = document.toObject<UserEntity>()
                callBack.execute(userEntity ?: UserEntity())
            }.addOnFailureListener(OnFailureListener { exception ->
                throw ErrorRequestException(exception.message.toString())
            }
            )
    }

    override fun postEntity(userEntity: UserEntity) {
        repository
            .postEntity(userEntity)
    }
}