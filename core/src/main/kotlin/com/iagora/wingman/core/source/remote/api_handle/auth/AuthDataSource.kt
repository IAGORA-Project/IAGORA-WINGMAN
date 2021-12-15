package com.iagora.wingman.core.source.remote.api_handle.auth

import retrofit2.Call
import com.iagora.wingman.core.source.remote.body.LoginBody
import com.iagora.wingman.core.source.remote.response.ResLogin

interface AuthDataSource {

    fun postLogin(body: LoginBody): Call<ResLogin>

}