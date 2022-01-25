package com.iagora.wingman.core.data.remote

import com.iagora.wingman.core.data.remote.response.BasicResponse
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST

interface CoreAPI {
    @Headers("auth: ini rahasia")
    @POST("auth/get-token")
    suspend fun sessid(): Response<BasicResponse>
}