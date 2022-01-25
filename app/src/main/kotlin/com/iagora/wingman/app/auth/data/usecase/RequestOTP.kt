package com.iagora.wingman.app.auth.data.usecase

import com.iagora.wingman.app.auth.domain.model.OtpResult
import com.iagora.wingman.app.auth.domain.repository.IAuthRepository
import com.iagora.wingman.app.auth.domain.usecase.IRequestOTP
import com.iagora.wingman.core.domain.util.ValidationUtil

class RequestOTP(private val repository: IAuthRepository) : IRequestOTP {

    override suspend fun invoke(phoneNumber: String): OtpResult {

        val phoneNumberError = ValidationUtil.validatePhoneNumber(phoneNumber)
        if (phoneNumber == null) {
            return OtpResult(phoneNumberError)

        }

        return OtpResult(result = repository.requestOTP(phoneNumber))
    }

}