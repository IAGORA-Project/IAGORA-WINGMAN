package com.iagora.wingman.app.auth.domain.model

import com.iagora.wingman.app.auth.data.remote.response.ResVerifyOtp
import com.iagora.wingman.core.data.remote.response.BasicResponse
import com.iagora.wingman.core.util.AuthError
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.SimpleResource

data class OtpResult(
    val phoneNumberError: AuthError? = null,
    val result: SimpleResource? = null
)

data class OtpVerifyResult(
    val message: String? = null,
    val result: SimpleResource? = null
)
