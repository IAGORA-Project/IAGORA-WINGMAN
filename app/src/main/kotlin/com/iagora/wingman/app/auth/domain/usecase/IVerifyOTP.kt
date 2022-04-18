package com.iagora.wingman.app.auth.domain.usecase

import com.iagora.wingman.app.auth.data.remote.request.VerifyOtpReq
import com.iagora.wingman.app.auth.data.remote.response.ResVerifyOtp
import com.iagora.wingman.app.auth.domain.model.OtpVerifyResult
import com.iagora.wingman.app.auth.domain.model.PersonalInfoResult

interface IVerifyOTP {
    suspend operator fun invoke(
        phoneNumber: String,
        otpCode: String
    ): OtpVerifyResult
}