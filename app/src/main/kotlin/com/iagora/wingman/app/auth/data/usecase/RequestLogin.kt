package com.iagora.wingman.app.auth.data.usecase

import com.google.firebase.messaging.FirebaseMessaging
import com.iagora.wingman.app.auth.domain.model.LoginResult
import com.iagora.wingman.app.auth.domain.repository.IAuthRepository
import com.iagora.wingman.app.auth.domain.usecase.IRequestLogin
import com.iagora.wingman.core.util.AuthError
import timber.log.Timber

class RequestLogin(private val repository: IAuthRepository) : IRequestLogin {
    override suspend fun invoke(
        phoneNumber: String,
        password: String,
    ): LoginResult {
        val deviceToken = getDeviceToken()
        val phoneNumberError = if (phoneNumber.isBlank()) AuthError.FieldEmpty else null
        val passwordError = if (password.isBlank()) AuthError.FieldEmpty else null
        val deviceTokenError = if (deviceToken.isEmpty()) AuthError.FieldEmpty else null

        Timber.e("token : $deviceToken, error: $deviceTokenError")

        if (phoneNumberError != null || passwordError != null ) {
            return LoginResult(phoneNumberError, passwordError, deviceTokenError)
        }



        return LoginResult(
            result = repository.requestLogin(phoneNumber, password, deviceToken)
        )
    }

    private fun getDeviceToken(): String = try {
        FirebaseMessaging.getInstance().token.result
    } catch (e: Throwable) {
        ""
    }
}