package com.iagora.wingman.app.auth.presentation.login

import com.iagora.wingman.core.util.Event

sealed class LoginEvent : Event() {
    object OnGetOtp : LoginEvent()
    object OnLogin : LoginEvent()
}
