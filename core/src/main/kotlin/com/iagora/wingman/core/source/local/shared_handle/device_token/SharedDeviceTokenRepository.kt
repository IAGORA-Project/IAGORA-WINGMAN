package com.iagora.wingman.core.source.local.shared_handle.device_token

import android.content.Context
import com.google.gson.Gson
import com.iagora.wingman.core.BuildConfig.KEY_SHARED_PREFERENCE_DT
import com.iagora.wingman.core.util.ChCrypto
import com.iagora.wingman.core.source.local.shared_preference.SharedPreference

class SharedDeviceTokenRepository(
    context: Context,
    gson: Gson
): SharedPreference(context, gson), SharedDeviceTokenDataSource{

    override fun getPreferencesGroup(): String  = "DEVICE_TOKEN_WINGMAN_GROUP"

    override fun saveDeviceToken(key: String, data: String) {
        saveDataString(key, data)
    }

    override fun getDeviceToken(key: String): String? {
        val getData = getString(key).toString()

        println("getDatagetData $getData")
        return ChCrypto.aesDecrypt(getData, KEY_SHARED_PREFERENCE_DT)
    }

    override fun clearDeviceToken(key: String): Boolean {
        return clearData(key)
    }


}