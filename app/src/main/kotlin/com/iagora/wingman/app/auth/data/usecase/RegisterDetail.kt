package com.iagora.wingman.app.auth.data.usecase

import com.iagora.wingman.app.auth.data.remote.request.RegistrationReq
import com.iagora.wingman.app.auth.domain.repository.IAuthRepository
import com.iagora.wingman.app.auth.domain.usecase.IRegisterDetail
import com.iagora.wingman.core.util.SimpleResource

class RegisterDetail(private val repository: IAuthRepository): IRegisterDetail {
    override suspend fun invoke(registrationReq: RegistrationReq): SimpleResource {
        return repository.registrationDetail(registrationReq)
    }
}