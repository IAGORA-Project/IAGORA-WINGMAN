package com.iagora.wingman.core.domain.repository

import com.iagora.wingman.core.util.SimpleResource

interface ICoreRepository {
    fun clearData(key: String): Boolean

    suspend fun getSESSID(): SimpleResource
}