package com.iagora.wingman.core.util


sealed class AuthError : Error() {
    object FieldEmpty : AuthError()
    object InputTooShort : AuthError()
    object InvalidEmail : AuthError()
    object InvalidPhoneNumber : AuthError()
    object InvalidOTP : AuthError()
}
