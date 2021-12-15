package com.iagora.wingman.core.source.remote.api_handle.auth


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import com.iagora.wingman.core.source.remote.body.LoginBody
import com.iagora.wingman.core.source.remote.response.ResLogin

interface AuthApi {

    @POST("login")
    fun post_login(
        @Body loginBody: LoginBody
    ): Call<ResLogin>
}