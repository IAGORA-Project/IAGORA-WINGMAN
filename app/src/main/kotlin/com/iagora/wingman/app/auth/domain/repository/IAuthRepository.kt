package com.iagora.wingman.app.auth.domain.repository

import com.google.android.gms.common.api.Response
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.SimpleResource

interface IAuthRepository {

    suspend fun requestLogin(
        phoneNumber: String,
        password: String,
    ): SimpleResource


    suspend fun requestOTP(
        phoneNumber: String
    ): SimpleResource


}