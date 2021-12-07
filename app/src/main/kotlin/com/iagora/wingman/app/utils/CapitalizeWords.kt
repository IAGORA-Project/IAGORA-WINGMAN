package com.iagora.wingman.app.utils

object CapitalizeWords {
    fun String.capitalizeWords(): String = split(" ").map { it.replaceFirstChar(Char::uppercase) }.joinToString(" ")
}