package com.iagora.wingman.core.domain.repository

interface ICoreRepository {
    fun clearData(key: String): Boolean
}