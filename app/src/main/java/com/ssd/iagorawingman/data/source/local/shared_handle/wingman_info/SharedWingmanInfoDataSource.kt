package com.ssd.iagorawingman.data.source.local.shared_handle.wingman_info

import com.ssd.iagorawingman.data.source.remote.response.ResGetWingmanInfo
import com.ssd.iagorawingman.data.source.remote.response.ResLogin

interface SharedWingmanInfoDataSource {

    fun saveWingmanInfo(key: String, data: String)

    fun getWingmanInfo(key: String): ResGetWingmanInfo?

    fun clearDataWingmanInfo(key: String): Boolean

}