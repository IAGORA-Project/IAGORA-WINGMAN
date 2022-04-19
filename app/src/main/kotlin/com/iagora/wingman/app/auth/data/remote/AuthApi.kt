package com.iagora.wingman.app.auth.data.remote

import com.iagora.wingman.app.auth.data.remote.request.LoginReq
import com.iagora.wingman.app.auth.data.remote.request.OtpReq
import com.iagora.wingman.app.auth.data.remote.request.RegistrationReq
import com.iagora.wingman.app.auth.data.remote.request.VerifyOtpReq
import com.iagora.wingman.app.auth.data.remote.response.ResAccessToken
import com.iagora.wingman.app.auth.data.remote.response.ResLogin
import com.iagora.wingman.app.auth.data.remote.response.ResRegistration
import com.iagora.wingman.app.auth.data.remote.response.ResVerifyOtp
import com.iagora.wingman.core.data.remote.response.BasicResponse
import com.iagora.wingman.core.data.remote.response.SimpleResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface AuthApi {
    @POST("api/v1/wingman/send-otp-wingman")
    suspend fun otp(
        @Body request: OtpReq,
    ): SimpleResponse

    @POST("api/v1/wingman/verify-otp")
    suspend fun verifyOtp(
        @Body request: VerifyOtpReq,
    ): Response<BasicResponse<ResVerifyOtp>>

    @GET("api/v1/wingman/get-access-token")
    suspend fun getAccessToken(
        @Header("x-refresh-token") token: String
    ): Response<BasicResponse<ResAccessToken>>


    @PUT("api/v1/wingman/{wingmanId}/complate-wingman-detail")
    suspend fun registerDetail(
        @Header("x-access-token") accesstoken: String,
        @Path("wingmanId") wingmanId: String,
        @Body regis: RegistrationReq
    ): Response<BasicResponse<ResRegistration>>

    @Multipart
    @JvmSuppressWildcards
    @PUT("api/v1/wingman/{wingmanId}/complate-wingman-document")
    suspend fun registerComplete(
        @Header("x-access-token") accesstoken: String,
        @Path("wingmanId") wingmanId: String,
        @Part ktp: MultipartBody.Part,
        @Part skck: MultipartBody.Part,
        @PartMap params: Map<String, RequestBody>
    ): Response<BasicResponse<ResRegistration>>

}