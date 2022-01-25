package com.iagora.wingman.core.domain.repository

import com.iagora.wingman.core.util.Resource

interface ICoreRepository {
    fun clearData(key: String): Boolean

    suspend fun getSESSID(): Resource<String>
}