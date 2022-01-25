package com.iagora.wingman.app.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.app.auth.data.usecase.RequestLogin
import com.iagora.wingman.app.auth.domain.usecase.IRequestOTP
import com.iagora.wingman.core.presentation.util.UiEvent
import com.iagora.wingman.core.util.Error
import com.iagora.wingman.core.util.Event
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.UiText
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val requestOTP: IRequestOTP,
    private val requestLogin: RequestLogin,
) : ViewModel() {

    private val _eventFLow = MutableSharedFlow<Event>()
    val eventFlow = _eventFLow.asSharedFlow()

    private val _loginState = MutableSharedFlow<Boolean>()
    val loginState = _loginState.asSharedFlow()

    private val _phoneNumberError = MutableSharedFlow<Error?>()
    val phoneNumberError = _phoneNumberError.asSharedFlow()

    private val _phoneNumberCompleted = MutableStateFlow("")

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
                is Resource.Success -> {
                    _eventFLow.emit(LoginEvent.OnGetOtp)
                    _phoneNumberCompleted.emit(phoneNumber)
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
}

