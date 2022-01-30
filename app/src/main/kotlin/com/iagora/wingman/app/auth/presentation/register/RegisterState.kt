package com.iagora.wingman.app.auth.presentation.register

import com.iagora.wingman.core.util.Event

sealed class RegisterState {
    object OnPreview : RegisterState()
    object OnSetDocument : RegisterState()
    object OnSetPersonalInfo : RegisterState()
}
