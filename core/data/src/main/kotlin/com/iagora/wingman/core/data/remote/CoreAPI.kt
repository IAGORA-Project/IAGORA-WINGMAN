package com.iagora.wingman.core.data.remote

import com.iagora.wingman.core.data.remote.response.SimpleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface CoreAPI {
    @Headers("auth: ini rahasia")
    @GET("auth/get-token")
    suspend fun sessid(): Response<SimpleResponse>
}