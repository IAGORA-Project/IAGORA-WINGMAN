package com.iagora.wingman.core.source.remote.api_handle.main_menu

import retrofit2.Call
import retrofit2.http.GET
import com.iagora.wingman.core.source.remote.response.ResGetWingmanInfo

interface MainMenuApi {

    @GET("info")
    fun get_wingmanInfo(
    ): Call<ResGetWingmanInfo>
}