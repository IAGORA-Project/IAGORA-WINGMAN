package com.iagora.wingman.app.auth.data.remote

import com.iagora.wingman.app.auth.data.remote.request.LoginReq
import com.iagora.wingman.app.auth.data.remote.request.OtpReq
import com.iagora.wingman.core.data.remote.response.BasicResponse
import com.iagora.wingman.core.data.remote.response.SimpleResponse
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface AuthApi {
    @POST("wingman/login-wingman")
    suspend fun login(
        @HeaderMap sessid: Map<String, String>,
        @Body request: LoginReq,
    ): BasicResponse<ResLogin>

    @POST("wingman/send-otp-wingman")
    suspend fun otp(
        @HeaderMap sessid: Map<String, String>,
        @Body request: OtpReq,
    ): SimpleResponse

}