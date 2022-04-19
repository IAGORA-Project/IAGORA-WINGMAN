package com.iagora.wingman.app.auth.domain.usecase

import com.iagora.wingman.app.auth.data.remote.request.RegistWingmanDetailReq
import com.iagora.wingman.app.auth.data.remote.request.RegistrationReq
import com.iagora.wingman.core.util.SimpleResource
import okhttp3.MultipartBody
import okhttp3.RequestBody


interface IRegisterComplate {
    suspend operator fun invoke(
        ktp: MultipartBody.Part,
        skck: MultipartBody.Part,
        map: Map<String, RequestBody>
    ): SimpleResource
}