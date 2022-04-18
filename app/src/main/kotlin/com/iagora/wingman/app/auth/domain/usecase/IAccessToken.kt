package com.iagora.wingman.app.auth.domain.usecase

import com.iagora.wingman.app.auth.domain.model.AccessTokenResult

interface IAccessToken {
    suspend operator fun invoke(
        token: String
    ):AccessTokenResult
}