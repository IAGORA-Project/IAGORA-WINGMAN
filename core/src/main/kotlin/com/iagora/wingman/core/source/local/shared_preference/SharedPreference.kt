package com.iagora.wingman.core.source.local.shared_preference

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

abstract class SharedPreference(context: Context, private val gson: Gson) {

    private lateinit var sharedPreferences: SharedPreferences

    init {
        initPreferences(context, getPreferencesGroup())
    }

    abstract fun getPreferencesGroup(): String

    private fun initPreferences(context: Context, preferencesGroup: String) {
        sharedPreferences = context.getSharedPreferences(preferencesGroup, Context.MODE_PRIVATE)
    }

    protected fun saveDataString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    protected fun getString(key: String): String? = sharedPreferences.getString(key, "")


    fun clearData(key: String): Boolean {
        if (sharedPreferences != null) {
            sharedPreferences.edit()?.remove(key)?.apply()
            return true
        }
        return false
    }

}