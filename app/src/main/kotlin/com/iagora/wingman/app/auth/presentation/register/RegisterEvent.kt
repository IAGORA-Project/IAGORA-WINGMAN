package com.iagora.wingman.app.auth.presentation.register

import com.iagora.wingman.core.util.Event

sealed class RegisterEvent : Event() {
    object OnPreview : RegisterEvent()
    object OnSetDocument : RegisterEvent()
    object OnSetPersonalInfo : RegisterEvent()
}
