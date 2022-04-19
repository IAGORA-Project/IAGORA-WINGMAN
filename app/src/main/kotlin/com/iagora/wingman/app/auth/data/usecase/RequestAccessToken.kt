package com.iagora.wingman.app.auth.data.usecase

import com.iagora.wingman.app.auth.data.repository.AuthRepository
import com.iagora.wingman.app.auth.domain.model.AccessTokenResult
import com.iagora.wingman.app.auth.domain.repository.IAuthRepository
import com.iagora.wingman.app.auth.domain.usecase.IAccessToken

class RequestAccessToken(private val repository:IAuthRepository ): IAccessToken {
    override suspend fun invoke(token: String): AccessTokenResult {
        val result = repository.getAccesToken(token)
        return AccessTokenResult(result)
    }
}