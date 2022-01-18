package com.iagora.wingman.app.auth.domain.repository

import com.iagora.wingman.core.util.SimpleResource

interface IAuthRepository {
    suspend fun requestLogin(
        phoneNumber: String,
        password: String,
        deviceToken: String,
    ): SimpleResource
}