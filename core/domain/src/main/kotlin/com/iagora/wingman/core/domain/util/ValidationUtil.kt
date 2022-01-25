package com.iagora.wingman.core.domain.util

import android.util.Patterns
import com.iagora.wingman.core.util.AuthError
import com.iagora.wingman.core.util.Constants.OTP_LENGTH

object ValidationUtil {

    private fun validateCleanText(rawText: String): AuthError? {
        val cleanText = rawText.trim()

        if (cleanText.isBlank()) {
            return AuthError.FieldEmpty
        }
        return null
    }

    fun validatePhoneNumber(phoneNumber: String): AuthError? {
        val cleanText = validateCleanText(phoneNumber)
        if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
            return AuthError.InvalidPhoneNumber
        }

        return cleanText
    }


    fun validateOTP(otp: String): AuthError? {
        val cleanText = validateCleanText(otp)
        if (otp.length != OTP_LENGTH) {
            return AuthError.InvalidOTP
        }
        return cleanText
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