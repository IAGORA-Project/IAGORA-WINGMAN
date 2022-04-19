package com.iagora.wingman.app.auth.domain.repository

import com.iagora.wingman.app.auth.data.remote.request.RegistWingmanDetailReq
import com.iagora.wingman.app.auth.data.remote.request.RegistrationReq
import com.iagora.wingman.app.auth.data.remote.response.ResVerifyOtp
import com.iagora.wingman.app.auth.data.remote.response.WingmanDetail
import com.iagora.wingman.core.data.remote.response.BasicResponse
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.SimpleResource
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface IAuthRepository {

    suspend fun requestOTP(
        phoneNumber: String,
    ): SimpleResource

    suspend fun verifyOtp(
        phoneNumber: String,
        otpCode: String
    ): SimpleResource

    suspend fun getAccesToken(
        token: String
    ): SimpleResource

    suspend fun registrationDetail(
        req: RegistrationReq
    ): SimpleResource

    suspend fun registrationComplate(
        ktp: MultipartBody.Part,
        skck: MultipartBody.Part,
        map: Map<String, RequestBody>
    ): SimpleResource
}