package com.example.sendmessageskotlin.activity

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.example.sendmessageskotlin.R
import com.example.sendmessageskotlin.common.StartActivityCallBack
import com.example.sendmessageskotlin.entity.UserEntity
import com.example.sendmessageskotlin.presenter.RegistrationPresenter
import com.example.sendmessageskotlin.service.NetworkIsConnectedService
import kotlin.math.roundToInt

class RegistrationActivity : AppCompatActivity() {

    private lateinit var registrationPresenter: RegistrationPresenter
    private var registrationBoolean: Boolean = false
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var textViewRegistration: TextView
    private lateinit var editTextName: EditText
    private lateinit var editTextNumberPassword: EditText
    private lateinit var buttonRegistration: Button
    private lateinit var checkBox: CheckBox
    private lateinit var startActivityCallBack: StartActivityCallBack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        init()
    }

    private fun init(){

        initView()
        isConnected()

        startActivityCallBack = object: StartActivityCallBack{
            override fun start() {
               runStartActivity()
            }
        }

        registrationPresenter = RegistrationPresenter(
            this@RegistrationActivity,
            startActivityCallBack
        )
    }

    private fun runStartActivity(){
        val intent = Intent(
            this@RegistrationActivity,
            ChatsActivity::class.java
        )
        startActivity(intent)
        finish()
    }

    private fun isConnected(){
        val networkIsConnectedService: NetworkIsConnectedService =
            ViewModelProvider(this)[NetworkIsConnectedService::class.java]
        networkIsConnectedService.isConnected(
            networkIsConnectedService,
            this,
            constraintLayout
        )
    }

    private fun initView() {
        constraintLayout = findViewById(R.id.constraintLayoutRegistrationActivity)
        textViewRegistration  = findViewById(R.id.textViewRegistration)
        editTextName  = findViewById(R.id.editTextName)
        editTextNumberPassword = findViewById(R.id.editTextNumberPassword)
        buttonRegistration = findViewById(R.id.buttonRegistration)
        checkBox = findViewById(R.id.checkBoxRegistration)

        buttonRegistration.setText(R.string.buttonRegistration_false)
        textViewRegistration.setText(R.string.textViewRegistration_false)
    }

    fun onClickButtonRegistration() {
        if (editTextName.text.toString().trim() == "") {
            Toast.makeText(
                applicationContext,
                R.string.toast_name,
                Toast.LENGTH_LONG
            ).show()
        } else if (editTextNumberPassword.text.toString().trim() == "") {
            Toast.makeText(
                applicationContext,
                R.string.toast_password,
                Toast.LENGTH_LONG
            ).show()
        } else {
            registrationPresenter
                .setIsRegistration(registrationBoolean)
                .setUserEntity(
                    createUserEntity(
                        registrationBoolean,
                        editTextName.text.toString().trim(),
                        editTextNumberPassword.text.toString().trim()
                    )
                ).signIn()
        }
    }

    fun onClickTextRegistration() {
        buttonStatus()
    }

    private fun buttonStatus() {
        if (registrationBoolean) {
            registrationBoolean = false
            buttonRegistration.setText(R.string.buttonRegistration_false)
            textViewRegistration.setText(R.string.textViewRegistration_false)
        } else {
            registrationBoolean = true
            buttonRegistration.setText(R.string.buttonRegistration_true)
            textViewRegistration.setText(R.string.textViewRegistration_true)
        }
    }

    private fun createUserEntity(
        isRegistration: Boolean,
        userName: String,
        userPassword: String
    ): UserEntity {

        return if(isRegistration){
            val userId: Int = (Math.random() * 1000000).roundToInt()
            UserEntity(
                userId,
                userName,
                userPassword
            )
        } else{
            UserEntity(
                userName,
                userPassword
            )
        }
    }

}