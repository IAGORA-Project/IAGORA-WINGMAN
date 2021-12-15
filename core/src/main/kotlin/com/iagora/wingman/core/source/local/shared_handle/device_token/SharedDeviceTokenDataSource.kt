package com.iagora.wingman.core.source.local.shared_handle.device_token

interface SharedDeviceTokenDataSource {
    fun saveDeviceToken(key: String, data: String)

    fun getDeviceToken(key: String): String?

    fun clearDeviceToken(key: String): Boolean
}