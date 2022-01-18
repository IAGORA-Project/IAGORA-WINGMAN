package com.iagora.wingman.core.util

sealed class UiEvent : Event() {
    data class CreateMessage(val uiText: UiText) : UiEvent()
    object OnLogin : UiEvent()
}