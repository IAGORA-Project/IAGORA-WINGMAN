package com.iagora.wingman.app.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.app.auth.domain.usecase.IAccessToken
import com.iagora.wingman.app.auth.domain.usecase.IRequestOTP
import com.iagora.wingman.app.auth.domain.usecase.IVerifyOTP
import com.iagora.wingman.app.auth.presentation.on_boarding.OnBoardingPage
import com.iagora.wingman.app.auth.presentation.register.RegisterState
import com.iagora.wingman.core.data.session.SessionManager
import com.iagora.wingman.core.domain.util.KEYPref
import com.iagora.wingman.core.presentation.util.Constants.ONE_SECOND
import com.iagora.wingman.core.presentation.util.UiEvent
import com.iagora.wingman.core.util.Error
import com.iagora.wingman.core.util.Event
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.UiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class LoginViewModel(
    private val requestOTP: IRequestOTP,
    private val verifyOTP: IVerifyOTP,
    private val accessToken: IAccessToken,
    private val sessionManager: SessionManager
    ) : ViewModel() {

    private val _onBoardingPage = MutableStateFlow<OnBoardingPage>(OnBoardingPage.LoginPage)
    val onBoardingpage = _onBoardingPage.asStateFlow()

    private val _eventSuccessFLow = MutableSharedFlow<Event>()
    val eventSuccesFlow = _eventSuccessFLow.asSharedFlow()

    private val _eventFLow = MutableSharedFlow<Event>()
    val eventFlow = _eventFLow.asSharedFlow()

    private val _loginState = MutableStateFlow(false)
    val loginState = _loginState.asStateFlow()

    private val _phoneNumberError = MutableStateFlow<Error?>(null)
    val phoneNumberError = _phoneNumberError.asStateFlow()

    private val _phoneNumberCompleted = MutableStateFlow("")
    val phoneNumberCompleted = _phoneNumberCompleted.asStateFlow()

    private val _delayToResendOtp = MutableStateFlow(30)
    val delayToResendOtp = _delayToResendOtp.asStateFlow()

    fun login(otp: String) {
        viewModelScope.launch {
        }
    }


    fun verifyOtp(phoneNumber: String, otpCode: String) {
        viewModelScope.launch {
            val request = verifyOTP(phoneNumber, otpCode)
            when (request.result) {
                is Resource.SuccessMessage -> {
                    getAccesToken(sessionManager.getFromPreference(KEYPref.TOKEN))
                    _eventSuccessFLow.emit(
                        UiEvent.CreateMessage(
                            request.result.uiText ?: UiText.unknownError()
                        )
                    )
                }
                is Resource.Error -> {
                    _eventFLow.emit(
                        UiEvent.CreateMessage(
                            request.result.uiText ?: UiText.unknownError()
                        )
                    )
                }
                else -> {}
            }

        }
    }

    fun getAccesToken(token: String) {
        viewModelScope.launch {
            val request = accessToken(token)
            when (request.result) {
                is Resource.SuccessMessage -> {
                    _onBoardingPage.emit(OnBoardingPage.MainPage)
                    _eventSuccessFLow.emit(
                        UiEvent.CreateMessage(request.result.uiText ?: UiText.unknownError())
                    )
                }
                is Resource.Error -> {
                    _eventFLow.emit(
                        UiEvent.CreateMessage(
                            request.result.uiText ?: UiText.unknownError()
                        )
                    )
                }
                else -> {}
            }
        }
    }

    fun setPhoneNumber(phoneNumber: String) {
        viewModelScope.launch {
            _loginState.emit(true)
            val request = requestOTP(phoneNumber)
            _loginState.emit(false)

            if (request.phoneNumberError != null) {
                _phoneNumberError.emit(request.phoneNumberError)

            } else {
                _phoneNumberError.emit(request.phoneNumberError)
            }

            when (request.result) {
                is Resource.SuccessMessage -> {
                    _eventSuccessFLow.emit(
                        UiEvent.CreateMessage(
                            request.result.uiText ?: UiText.unknownError()
                        )
                    )
                    _eventFLow.emit(LoginEvent.OnGetOtp)
                    _phoneNumberCompleted.emit(phoneNumber)

                    _delayToResendOtp.emit(30)
                    setDelayToResendOtp()
                }
                is Resource.Error -> {
                    _eventFLow.emit(
                        UiEvent.CreateMessage(
                            request.result.uiText ?: UiText.unknownError()
                        )
                    )
                }
                else -> {}
            }
        }
    }

    private suspend fun setDelayToResendOtp() {
        while (delayToResendOtp.value > 0) {
            delay(ONE_SECOND)
            _delayToResendOtp.value = delayToResendOtp.value.dec()
        }
    }
}

