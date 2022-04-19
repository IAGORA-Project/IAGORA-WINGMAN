package com.iagora.wingman.core.data.usecase

import com.iagora.wingman.core.data.session.SessionManager
import com.iagora.wingman.core.domain.usecase.ISavePref

class SavePref(private val sessionManager: SessionManager) : ISavePref {
    override fun invoke(key: String, value: String):Boolean = sessionManager.saveToPreference(key, value)
}