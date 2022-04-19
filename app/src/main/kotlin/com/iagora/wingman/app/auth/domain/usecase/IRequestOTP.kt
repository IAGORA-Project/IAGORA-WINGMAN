package com.iagora.wingman.app.auth.domain.usecase

import com.iagora.wingman.app.auth.domain.model.OtpResult

interface IRequestOTP {
    suspend operator fun invoke(
        phoneNumber: String
    ):OtpResult
}