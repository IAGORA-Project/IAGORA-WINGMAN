package com.iagora.wingman.core.source.local.shared_handle.wingman_info

import android.content.Context
import com.google.gson.Gson
import com.iagora.wingman.core.BuildConfig.KEY_CRYPTO_AUTH
import com.iagora.wingman.core.ChCrypto
import com.iagora.wingman.core.source.local.shared_preference.SharedPreference
import com.iagora.wingman.core.source.remote.response.ResGetWingmanInfo

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