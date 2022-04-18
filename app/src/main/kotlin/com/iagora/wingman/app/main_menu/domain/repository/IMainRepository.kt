package com.iagora.wingman.app.main_menu.domain.repository

import com.iagora.wingman.app.main_menu.domain.model.response.WingmanInfo
import com.iagora.wingman.core.util.Resource

interface IMainRepository {
    fun getWingmanInfo(key: String): WingmanInfo?
    suspend fun getNewWingmanInfo(): Resource<WingmanInfo>
}