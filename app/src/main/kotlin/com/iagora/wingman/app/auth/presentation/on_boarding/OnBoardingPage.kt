package com.iagora.wingman.app.auth.presentation.on_boarding

import com.iagora.wingman.core.util.Event
import com.iagora.wingman.core.presentation.util.UiEvent

sealed class OnBoardingPage : Event() {
    object MainPage : OnBoardingPage()
    object LoginPage : OnBoardingPage()
    data class Failed(val showError: UiEvent.CreateMessage) : OnBoardingPage()
}