package com.iagora.wingman.core.source.remote.api_handle.main_menu

import retrofit2.Call
import com.iagora.wingman.core.source.remote.response.ResGetWingmanInfo

interface MainMenuDataSource {

    fun getWingmanInfo(): Call<ResGetWingmanInfo>

}