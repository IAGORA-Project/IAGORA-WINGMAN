package com.iagora.wingman.core.data.usecase

import com.iagora.wingman.core.data.session.SessionManager
import com.iagora.wingman.core.domain.usecase.IGetPref

class GetPref(private val sessionManager: SessionManager) : IGetPref {
    override fun invoke(key: String) = sessionManager.getFromPreference(key)
}