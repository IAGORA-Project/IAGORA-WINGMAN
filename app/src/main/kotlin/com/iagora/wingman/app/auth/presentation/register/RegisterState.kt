package com.iagora.wingman.app.auth.presentation.register

import com.iagora.wingman.core.util.Event

sealed class RegisterState {
    object OnSetPhoneOtp: RegisterState()
    object OnSetVerifOtp: RegisterState()
    object OnSetDocument : RegisterState()
    object OnSetPersonalInfo : RegisterState()
    object OnPreview : RegisterState()
}
