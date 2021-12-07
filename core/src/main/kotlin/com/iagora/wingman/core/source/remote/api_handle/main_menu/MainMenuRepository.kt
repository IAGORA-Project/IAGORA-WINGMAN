package com.iagora.wingman.core.source.remote.api_handle.main_menu

import retrofit2.Call
import com.iagora.wingman.core.source.remote.response.ResGetWingmanInfo

class MainMenuRepository(
    private val mainMenuApi: MainMenuApi
): MainMenuDataSource {

    override fun getWingmanInfo(): Call<ResGetWingmanInfo> {
       return mainMenuApi.get_wingmanInfo()
    }

}