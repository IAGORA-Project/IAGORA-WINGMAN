package com.iagora.wingman.app.auth.domain.usecase

import com.iagora.wingman.app.auth.data.remote.request.RegistrationReq
import com.iagora.wingman.core.util.SimpleResource
import okhttp3.RequestBody

interface IRegisterDetail {
    suspend operator fun invoke(
       registrationReq: RegistrationReq
    ): SimpleResource
}