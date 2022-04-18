package com.iagora.wingman.app.auth.domain.usecase

import com.iagora.wingman.app.auth.domain.model.PersonalInfoResult

interface ISetPersonalInfo {
    operator fun invoke(
        fullName: String,
        email: String,
        address: String,
    ): PersonalInfoResult
}