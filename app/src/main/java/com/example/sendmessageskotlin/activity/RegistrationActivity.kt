package com.example.sendmessageskotlin.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.sendmessageskotlin.R
import com.example.sendmessageskotlin.entity.UserEntity
import com.example.sendmessageskotlin.presenter.RegistrationPresenter
import com.google.firebase.firestore.FirebaseFirestore
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        init()
    }

    private fun init() {
        registrationPresenter = RegistrationPresenter(applicationContext)
        constraintLayout = findViewById(R.id.constraintLayoutRegistrationActivity)
        textViewRegistration  = findViewById(R.id.textViewRegistration)
        editTextName  = findViewById(R.id.editTextName)
        editTextNumberPassword = findViewById(R.id.editTextNumberPassword)
        buttonRegistration = findViewById(R.id.buttonRegistration)
        checkBox = findViewById(R.id.checkBoxRegistration)

        buttonRegistration.setText(R.string.buttonRegistration_false)
        textViewRegistration.setText(R.string.textViewRegistration_false)
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
        userName: String,
        userPassword: String
    ): UserEntity {
        val userId: Int = (Math.random() * 1000000).roundToInt()
        return UserEntity(
            userId,
            userName,
            userPassword
        )
    }

    fun onClickButtonRegistration(view: View) {
        if (editTextName.text.toString().trim().equals("")) {
            Toast.makeText(
                applicationContext,
                R.string.toast_name,
                Toast.LENGTH_LONG
            ).show()
        } else if (editTextNumberPassword.text.toString().trim().equals("")) {
            Toast.makeText(
                applicationContext,
                R.string.toast_password,
                Toast.LENGTH_LONG
            ).show()
        } else {
            registrationPresenter
                .setIsRegistration(registrationBoolean)
                .setUserPassword(editTextNumberPassword.text.toString().trim())

            if (registrationBoolean) {
                registrationPresenter
                    .setUserEntity(
                        createUserEntity(
                            editTextName.text.toString().trim(),
                            editTextNumberPassword.text.toString().trim()
                        )
                    )
            }

            registrationPresenter
                .signIn(editTextName.text.toString().trim())
        }
    }

    fun onClickTextRegistration(view: View) {
        buttonStatus()
    }

}