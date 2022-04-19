package com.iagora.wingman.core.data.util

import android.content.SharedPreferences
import com.google.gson.Gson

object PrefSetting {

    fun SharedPreferences.saveDataString(key: String, value: Any) {

        val toJson = Gson().toJson(value)
        val editor = edit()
        editor.putString(key, toJson)
        editor.apply()
    }




    fun SharedPreferences.clearData(key: String): Boolean = try {
        edit()?.remove(key)?.apply()
        true
    } catch (e: Throwable) {
        false
    }
}