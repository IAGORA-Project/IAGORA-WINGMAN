package com.iagora.wingman.core.util

typealias SimpleResource = Resource<Unit>

sealed class Resource<T>(val data: T? = null, val uiText: UiText? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class SuccessMessage<T>(uiText: UiText, data: T? = null): Resource<T>(data, uiText)
    class Error<T>(uiText: UiText, data: T? = null): Resource<T>(data, uiText)
}

