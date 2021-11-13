package com.ssd.iagorawingman.data.source.remote.api_handle.main_menu

import com.ssd.iagorawingman.data.source.remote.response.ResGetWingmanInfo
import retrofit2.Call

class MainMenuRepository(
    private val mainMenuApi: MainMenuApi
): MainMenuDataSource {

    override fun getWingmanInfo(): Call<ResGetWingmanInfo> {
       return mainMenuApi.get_wingmanInfo()
    }

}