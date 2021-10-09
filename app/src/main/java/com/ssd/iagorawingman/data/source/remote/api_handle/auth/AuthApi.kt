package com.ssd.iagorawingman.data.source.remote.api_handle.auth


import com.ssd.iagorawingman.data.source.remote.body.LoginBody
import com.ssd.iagorawingman.data.source.remote.response.ResLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("login")
    fun post_login(
        @Body loginBody: LoginBody
    ): Call<ResLogin>
}