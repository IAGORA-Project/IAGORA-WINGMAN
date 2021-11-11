package com.ssd.iagorawingman.data.source.remote.api_handle.main_menu

import com.ssd.iagorawingman.data.source.remote.response.ResGetWingmanInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MainMenuApi {

    @GET("info")
    fun get_wingmanInfo(
        @Header("Authorization") authorization: String,
    ): Call<ResGetWingmanInfo>
}