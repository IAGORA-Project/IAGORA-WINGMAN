package com.iagora.wingman.app.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.app.auth.domain.usecase.IRequestLogin
import com.iagora.wingman.app.auth.domain.usecase.IRequestOTP
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

class LoginViewModel(
    private val requestOTP: IRequestOTP,
    private val requestLogin: IRequestLogin,
) : ViewModel() {

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
            _loginState.emit(true)
            val request = requestLogin(phoneNumber = _phoneNumberCompleted.value, otp = otp)
            _loginState.emit(false)

            when (request.result) {
                is Resource.Success -> {
                    _eventFLow.emit(LoginEvent.OnLogin)
                }

                is Resource.Error -> {
                    _eventFLow.emit(
                        UiEvent.CreateMessage(
                            request.result.uiText ?: UiText.unknownError()
                        )
                    )
                    _delayToResendOtp.emit(0)
                }
                else -> {

                }
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
                is Resource.Success -> {
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

