package com.iagora.wingman.app.auth.data.usecase

import com.iagora.wingman.app.auth.data.remote.request.RegistWingmanDetailReq
import com.iagora.wingman.app.auth.domain.repository.IAuthRepository
import com.iagora.wingman.app.auth.domain.usecase.IRegisterComplate
import com.iagora.wingman.core.util.SimpleResource
import okhttp3.MultipartBody
import okhttp3.RequestBody

class RegisterComplate(private val repository: IAuthRepository): IRegisterComplate {
    override suspend fun invoke(
        ktp: MultipartBody.Part,
        skck: MultipartBody.Part,
        map: Map<String, RequestBody>
    ): SimpleResource {
        return repository.registrationComplate(ktp, skck, map)
    }
}