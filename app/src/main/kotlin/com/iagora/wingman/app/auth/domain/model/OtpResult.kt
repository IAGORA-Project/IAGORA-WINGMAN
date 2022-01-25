package com.iagora.wingman.app.auth.domain.model

import com.iagora.wingman.core.util.AuthError
import com.iagora.wingman.core.util.SimpleResource

data class OtpResult(
    val phoneNumberError: AuthError? = null,
    val result: SimpleResource? = null
)