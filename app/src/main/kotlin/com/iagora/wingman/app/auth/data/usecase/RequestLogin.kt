package com.iagora.wingman.app.auth.data.usecase

import com.iagora.wingman.app.auth.domain.model.LoginResult
import com.iagora.wingman.app.auth.domain.repository.IAuthRepository
import com.iagora.wingman.app.auth.domain.usecase.IRequestLogin
import com.iagora.wingman.core.domain.util.ValidationUtil

class RequestLogin(private val repository: IAuthRepository) : IRequestLogin {
    override suspend fun invoke(
        phoneNumber: String,
        otp: String,
    ): LoginResult {

        val phoneNumberError = ValidationUtil.validatePhoneNumber(phoneNumber)
        val otpError = ValidationUtil.validateOTP(otp)

        if (phoneNumberError != null || otpError != null) {
            return LoginResult(phoneNumberError, otpError)
        }

        return LoginResult(result = repository.requestLogin(phoneNumber.trim(), otp.trim()))
    }


}