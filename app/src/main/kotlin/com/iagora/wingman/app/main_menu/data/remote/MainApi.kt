package com.iagora.wingman.app.main_menu.data.remote


import com.iagora.wingman.app.main_menu.data.remote.response.ResWingmanInfo
import retrofit2.http.GET

interface MainApi {
    @GET("info")
    suspend fun getWingmanInfo(
    ): ResWingmanInfo
}