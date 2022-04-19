package com.iagora.wingman.app.auth.domain.model

import com.iagora.wingman.core.util.AuthError

data class PersonalInfoResult(
    val fullNameError: AuthError? = null,
    val emailError: AuthError? = null,
    val addressError: AuthError? = null,
    val cityError: AuthError? = null,
    val marketError: AuthError? = null,
    val result: PersonalInfo? = null,
)

data class PersonalInfo(
    val fullName: String,
    val email: String,
    val address: String
)
