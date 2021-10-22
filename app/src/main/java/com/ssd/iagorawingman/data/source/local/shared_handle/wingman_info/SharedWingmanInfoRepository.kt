package com.ssd.iagorawingman.data.source.local.shared_handle.wingman_info

import android.content.Context
import com.google.gson.Gson
import com.ssd.iagora_user.data.source.local.shared_preference.SharedPreference
import com.ssd.iagorawingman.BuildConfig.KEY_CRYPTO_AUTH
import com.ssd.iagorawingman.BuildConfig.KEY_SHARED_PREFERENCE_AUTH
import com.ssd.iagorawingman.data.source.local.shared_handle.auth.SharedAuthDataSource
import com.ssd.iagorawingman.data.source.remote.response.ResGetWingmanInfo
import com.ssd.iagorawingman.data.source.remote.response.ResLogin
import com.ssd.iagorawingman.utils.ChCrypto

class SharedWingmanInfoRepository(
    context: Context,
    gson: Gson
): SharedPreference(context, gson), SharedWingmanInfoDataSource {

    override fun getPreferencesGroup(): String  = "WINGMAN_INFOMATION"

    override fun saveWingmanInfo(key: String, data: String) {
        println("DKLJDKJDDJKDJKDJ $data")
        saveDataString(key, data)
    }

    override fun getWingmanInfo(key: String): ResGetWingmanInfo? {
        val getData = getString(key).toString()
        val decrypt = ChCrypto.aesDecrypt(getData, KEY_CRYPTO_AUTH)

        return Gson().fromJson(decrypt, ResGetWingmanInfo::class.java)
    }

    override fun clearDataWingmanInfo(key: String): Boolean {
        return clearData(key)
    }
}