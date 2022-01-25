package com.iagora.wingman.app.auth.data.remote

import com.iagora.wingman.app.auth.data.remote.request.LoginReq
import com.iagora.wingman.app.auth.data.remote.request.OtpReq
import com.iagora.wingman.app.auth.data.remote.response.ResLogin
import com.iagora.wingman.core.data.remote.response.BasicResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun login(
        @Body request: LoginReq
    ): ResLogin

    @POST("login")
    suspend fun otp(
        @Body request: OtpReq
    ): BasicResponse


}