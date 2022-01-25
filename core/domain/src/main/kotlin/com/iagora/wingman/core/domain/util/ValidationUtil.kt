package com.iagora.wingman.core.domain.util

import com.iagora.wingman.core.util.AuthError

object ValidationUtil {


    fun validatePhoneNumber(phoneNumber: String): AuthError? {
        val cleanText = phoneNumber.trim()

        if (cleanText.isBlank()) {
            return AuthError.FieldEmpty
        }
        val regex = "^628[1-9][0-9]{6,9}$".toRegex()

        if (!regex.matches(phoneNumber)) {
            return AuthError.InvalidPhoneNumber
        }

        return null
    }


    fun validateOTP(otp: String): AuthError? {
        val cleanText = otp.trim()

        if (cleanText.isBlank()) {
            return AuthError.FieldEmpty
        }

        return null
    }

//    fun validateEmail(email: String): AuthError? {
//        val trimmedEmail = email.trim()
//
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            return AuthError.InvalidEmail
//        }
//        if (trimmedEmail.isBlank()) {
//            return AuthError.FieldEmpty
//        }
//        return null
//    }

//    fun validateUsername(username: String): AuthError? {
//        val trimmedUsername = username.trim()
//        if (trimmedUsername.length < Constants.MIN_USERNAME_LENGTH) {
//            return AuthError.InputTooShort
//        }
//        if (trimmedUsername.isBlank()) {
//            return AuthError.FieldEmpty
//        }
//        return null
//    }
//
//    fun validatePassword(password: String): AuthError? {
//        val capitalLettersInPassword = password.any { it.isUpperCase() }
//        val numberInPassword = password.any { it.isDigit() }
//        if (!capitalLettersInPassword || !numberInPassword) {
//            return AuthError.InvalidPassword
//        }
//        if (password.length < Constants.MIN_PASSWORD_LENGTH) {
//            return AuthError.InputTooShort
//        }
//        if (password.isBlank()) {
//            return AuthError.FieldEmpty
//        }
//        return null
//    }
}