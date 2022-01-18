package com.iagora.wingman.app.auth.data.repository

import android.content.SharedPreferences
import com.iagora.wingman.app.R
import com.iagora.wingman.app.auth.data.remote.AuthApi
import com.iagora.wingman.app.auth.data.remote.request.LoginReq
import com.iagora.wingman.app.auth.domain.repository.IAuthRepository
import com.iagora.wingman.core.data.util.ChCrypto
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.SimpleResource
import com.iagora.wingman.core.util.UiText
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class AuthRepository(
    private val api: AuthApi,
    private val sharedPreferences: SharedPreferences,
    private val keyCryptAuth: String,
    private val keySharedAuth: String,
) : IAuthRepository {

    override suspend fun requestLogin(
        phoneNumber: String,
        password: String,
        deviceToken: String
    ): SimpleResource {
        val request = LoginReq(phoneNumber, password, deviceToken)
        return try {
            val response = api.login(request)
            val token = response.body()?.success?.token ?: ""

            val server = response.headers().get("Server")

            Timber.e("Server : $server")

            if (response.isSuccessful) {
                val encryptToken =
                    ChCrypto.aesEncrypt(token, keyCryptAuth)
                sharedPreferences.edit()
                    .putString(keySharedAuth, encryptToken)
                    .apply()

                Resource.Success(Unit)
            } else {
                Timber.e("ERROR LOGIN")
                Resource.Error(UiText.DynamicString("Error occurred state : ${response.message()}"))
            }
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

}