package com.ssd.iagorawingman.data.source.local.shared_handle.auth

import android.content.Context
import com.google.gson.Gson
import com.ssd.iagora_user.data.source.local.shared_preference.SharedPreference
import com.ssd.iagorawingman.BuildConfig.KEY_CRYPTO_AUTH
import com.ssd.iagorawingman.BuildConfig.KEY_SHARED_PREFERENCE_AUTH
import com.ssd.iagorawingman.data.source.remote.response.ResLogin
import com.ssd.iagorawingman.utils.ChCrypto

class SharedAuthRepository(
    context: Context,
    gson: Gson
): SharedPreference(context, gson), SharedAuthDataSource {

    override fun getPreferencesGroup(): String  = "AUTH_WINGMAN_GROUP"

    override fun saveAuth(key: String, data: String) {
        saveDataString(key, data)
    }

    override fun getAuth(key: String): ResLogin? {
        val getData = getString(key).toString()
        val decrypt = ChCrypto.aesDecrypt(getData, KEY_CRYPTO_AUTH)

        return Gson().fromJson(decrypt, ResLogin::class.java)
    }

    override fun clearDataAuth(key: String): Boolean {
        return clearData(key)
    }


}