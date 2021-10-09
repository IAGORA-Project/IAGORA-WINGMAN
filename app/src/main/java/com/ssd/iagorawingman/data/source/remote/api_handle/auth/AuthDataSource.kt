package com.ssd.iagorawingman.data.source.remote.api_handle.auth

import com.ssd.iagorawingman.data.source.remote.body.LoginBody
import com.ssd.iagorawingman.data.source.remote.response.ResLogin
import retrofit2.Call

interface AuthDataSource {

    fun postLogin(body: LoginBody): Call<ResLogin>

}