package com.iagora.wingman.app.auth.presentation.splash

import androidx.lifecycle.ViewModel
import com.iagora.wingman.core.domain.usecase.IGetAuthToken
import com.iagora.wingman.core.presentation.util.UiEvent
import com.iagora.wingman.core.util.UiText

class SplashViewModel(
    private val getAuthToken: IGetAuthToken,
) : ViewModel() {



    fun loadLogin() =
        try {
            if (getAuthToken().isNotEmpty()) {
                OnBoardingPage.MainPage
            } else {
                OnBoardingPage.LoginPage
            }
        } catch (e: Throwable) {
            OnBoardingPage.Failed(UiEvent.CreateMessage(UiText.DynamicString("Error occurred $e")))
        }

}