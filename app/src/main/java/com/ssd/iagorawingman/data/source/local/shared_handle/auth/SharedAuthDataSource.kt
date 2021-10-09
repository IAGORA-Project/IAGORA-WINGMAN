package com.ssd.iagorawingman.data.source.local.shared_handle.auth

import com.ssd.iagorawingman.data.source.remote.response.ResLogin

interface SharedAuthDataSource {

    fun saveAuth(key: String, data: String)

    fun getAuth(key: String): ResLogin?

    fun clearDataAuth(key: String): Boolean

}