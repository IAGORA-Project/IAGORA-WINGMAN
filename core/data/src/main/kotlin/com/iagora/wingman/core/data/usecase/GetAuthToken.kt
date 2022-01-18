package com.iagora.wingman.core.data.usecase

import android.content.SharedPreferences
import com.iagora.wingman.core.domain.usecase.IGetAuthToken
import com.iagora.wingman.core.data.util.ChCrypto

class GetAuthToken(
    private val sharedPreferences: SharedPreferences,
    private val keySharedAuth: String,
    private val keyCryptAuth: String
) : IGetAuthToken {

    override fun invoke(): String {
        val getToken = sharedPreferences.getString(keySharedAuth, "") ?: ""
        return ChCrypto.aesDecrypt(getToken, keyCryptAuth)
    }
}