package com.example.sendmessageskotlin.common

import android.content.Context
import android.content.SharedPreferences

object Data {

    private const val SETTINGS_PREFERENCES = "settings"
    const val USERNAME = "username"
    const val SAVE_USERNAME = "saveUsername"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            SETTINGS_PREFERENCES,
            Context.MODE_PRIVATE
        )
    }

    fun putStringPreferences(context: Context, key: String, value: String) {
        val editor = getSharedPreferences(context).edit()
        editor
            .putString(key, value)
            .apply()
    }

    fun getStringPreferences(context: Context, key: String): String {
        return getSharedPreferences(context)
            .getString(key, "")
            .toString()
    }

    fun putBooleanPreferences(context: Context, key: String, value: Boolean) {
        val editor = getSharedPreferences(context).edit()
        editor
            .putBoolean(key, value)
            .apply()
    }

    fun getBooleanPreferences(context: Context, key: String): Boolean {
        return getSharedPreferences(context)
            .getBoolean(key, false)
    }

    fun removePreferences(context: Context, key: String) {
        getSharedPreferences(context)
            .edit()
            .remove(key)
            .apply()
    }

    fun existsPreferences(context: Context, key: String): Boolean {
        return getSharedPreferences(context)
            .contains(key)
    }
}