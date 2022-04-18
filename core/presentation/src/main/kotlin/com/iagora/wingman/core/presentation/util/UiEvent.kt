package com.iagora.wingman.core.presentation.util

import com.iagora.wingman.core.util.Event
import com.iagora.wingman.core.util.UiText

sealed class UiEvent : Event() {
    data class CreateMessage(val uiText: UiText) : UiEvent()
}