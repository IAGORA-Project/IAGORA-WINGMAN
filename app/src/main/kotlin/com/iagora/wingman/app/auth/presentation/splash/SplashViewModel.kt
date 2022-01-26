package com.iagora.wingman.app.auth.presentation.splash

import androidx.lifecycle.ViewModel
import com.iagora.wingman.core.domain.session.SessionManager
import com.iagora.wingman.core.presentation.util.UiEvent
import com.iagora.wingman.core.util.UiText

class SplashViewModel(
    private val sessionManager: SessionManager,
) : ViewModel() {

    fun performOnBoardPage() =
        try {
            if (sessionManager.getToken().isNotEmpty()) {
                OnBoardingPage.MainPage
            } else {
                OnBoardingPage.LoginPage
            }
        } catch (e: Throwable) {
            OnBoardingPage.Failed(UiEvent.CreateMessage(UiText.DynamicString("Error occurred $e")))
        }

}