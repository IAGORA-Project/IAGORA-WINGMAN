package com.ssd.iagorawingman.data.source.remote.api_handle.auth

import com.ssd.iagorawingman.data.source.remote.body.LoginBody
import com.ssd.iagorawingman.data.source.remote.response.ResLogin
import retrofit2.Call

class AuthReposiroty(
    private val authApi: AuthApi
): AuthDataSource {

    override fun postLogin(body: LoginBody): Call<ResLogin> {
        return authApi.post_login(body)
    }
}