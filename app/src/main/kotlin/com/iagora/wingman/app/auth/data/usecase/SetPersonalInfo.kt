package com.iagora.wingman.app.auth.data.usecase

import com.iagora.wingman.app.auth.domain.model.PersonalInfo
import com.iagora.wingman.app.auth.domain.model.PersonalInfoResult
import com.iagora.wingman.app.auth.domain.usecase.ISetPersonalInfo
import com.iagora.wingman.core.domain.util.ValidationUtil
import com.iagora.wingman.core.util.AuthError

class SetPersonalInfo : ISetPersonalInfo {
    override fun invoke(
        fullName: String,
        email: String,
        address: String
    ): PersonalInfoResult {

        val fullNameError = if (fullName.trim().isBlank()) AuthError.FieldEmpty else null
        val emailError = ValidationUtil.validateEmail(email)
        val addressError = if (address.trim().isBlank()) AuthError.FieldEmpty else null

        if (fullNameError != null || emailError != null || addressError != null) {
            return PersonalInfoResult(
                fullNameError = fullNameError,
                emailError = emailError,
                addressError = addressError
            )
        }

        return PersonalInfoResult(
            result = PersonalInfo(
                fullName.trim(),
                email.trim(),
                address.trimEnd()
            )
        )
    }
}