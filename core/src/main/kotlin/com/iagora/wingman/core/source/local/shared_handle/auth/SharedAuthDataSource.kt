package com.iagora.wingman.core.source.local.shared_handle.auth

import com.iagora.wingman.core.source.remote.response.ResLogin


interface SharedAuthDataSource {

    fun saveAuth(key: String, data: String)

    fun getAuth(key: String): ResLogin?

    fun clearDataAuth(key: String): Boolean

}