package com.iagora.wingman.core.source.remote.api_handle.auth

import retrofit2.Call
import com.iagora.wingman.core.source.remote.body.LoginBody
import com.iagora.wingman.core.source.remote.response.ResLogin

class AuthReposiroty(
    private val authApi: AuthApi
): AuthDataSource {

    override fun postLogin(body: LoginBody): Call<ResLogin> {
        return authApi.post_login(body)
    }
}