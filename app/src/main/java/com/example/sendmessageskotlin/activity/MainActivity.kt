package com.example.sendmessageskotlin.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sendmessageskotlin.common.Data

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isSave()
    }

    private fun isSave(){
        if(
            Data
                .existsPreferences(this, Data.SAVE_USERNAME)
        ){
            Data.putStringPreferences(
                this,
                Data.USERNAME,
                Data.getStringPreferences(this, Data.SAVE_USERNAME)
            )
            runStartActivity(ChatsActivity::class.java)
        } else{
            runStartActivity(RegistrationActivity::class.java)
        }
    }

    private fun runStartActivity(classWhere: Class<*>) {
        val intent = Intent(
            this@MainActivity,
            classWhere
        )
        startActivity(intent)
        finish()
    }
}