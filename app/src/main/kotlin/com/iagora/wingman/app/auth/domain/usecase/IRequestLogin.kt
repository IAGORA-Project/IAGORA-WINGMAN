package com.iagora.wingman.app.auth.domain.usecase

import com.iagora.wingman.app.auth.domain.model.LoginResult


interface IRequestLogin {
    suspend operator fun invoke(
        phoneNumber: String,
        otp: String,
    ): LoginResult
}