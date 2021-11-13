package com.ssd.iagorawingman.data.source.remote.api_handle.main_menu

import com.ssd.iagorawingman.data.source.remote.response.ResGetWingmanInfo
import retrofit2.Call

interface MainMenuDataSource {

    fun getWingmanInfo(): Call<ResGetWingmanInfo>

}