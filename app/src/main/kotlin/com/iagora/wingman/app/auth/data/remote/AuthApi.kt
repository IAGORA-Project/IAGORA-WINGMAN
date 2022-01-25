package com.iagora.wingman.app.auth.data.remote

import com.iagora.wingman.app.auth.data.remote.request.LoginReq
import com.iagora.wingman.app.auth.data.remote.request.OtpReq
import com.iagora.wingman.app.auth.data.remote.response.ResLogin
import com.iagora.wingman.core.data.remote.response.BasicResponse
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface AuthApi {
    @POST("user/login-user")
    suspend fun login(
        @HeaderMap sessid: Map<String, String>,
        @Body request: LoginReq,
    ): ResLogin

    @POST("user/send-otp-user")
    suspend fun otp(
        @HeaderMap sessid: Map<String, String>,
        @Body request: OtpReq,
    ): BasicResponse


}