package com.iagora.wingman.app.auth.data.usecase

import com.iagora.wingman.app.auth.data.remote.request.VerifyOtpReq
import com.iagora.wingman.app.auth.data.remote.response.ResVerifyOtp
import com.iagora.wingman.app.auth.data.repository.AuthRepository
import com.iagora.wingman.app.auth.domain.model.OtpVerifyResult
import com.iagora.wingman.app.auth.domain.repository.IAuthRepository
import com.iagora.wingman.app.auth.domain.usecase.IVerifyOTP

class VerifyOTP(private val repository: IAuthRepository) : IVerifyOTP {
    override suspend fun invoke(phoneNumber: String, otpCode: String): OtpVerifyResult {
        val response = repository.verifyOtp(phoneNumber, otpCode)
        return OtpVerifyResult(result = response)
    }
}