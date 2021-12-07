package com.iagora.wingman.core.source.local.shared_handle.wingman_info

import com.iagora.wingman.core.source.remote.response.ResGetWingmanInfo

interface SharedWingmanInfoDataSource {

    fun saveWingmanInfo(key: String, data: String)

    fun getWingmanInfo(key: String): ResGetWingmanInfo?

    fun clearDataWingmanInfo(key: String): Boolean

}