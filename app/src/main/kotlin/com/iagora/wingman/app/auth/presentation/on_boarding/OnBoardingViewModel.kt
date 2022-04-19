package com.iagora.wingman.app.auth.presentation.on_boarding

import androidx.lifecycle.ViewModel
import com.iagora.wingman.core.domain.usecase.IGetPref
import com.iagora.wingman.core.domain.util.KEYPref
import com.iagora.wingman.core.presentation.util.UiEvent
import com.iagora.wingman.core.util.UiText
import timber.log.Timber

class OnBoardingViewModel(
    private val getPref: IGetPref
) : ViewModel() {

    fun performOnBoardPage() =
        try {
            if (getPref(KEYPref.TOKEN).isNotEmpty()) {
                OnBoardingPage.MainPage
            } else {
                OnBoardingPage.LoginPage
            }
        } catch (e: Throwable) {
            OnBoardingPage.Failed(UiEvent.CreateMessage(UiText.DynamicString("Error occurred $e")))
        }

}