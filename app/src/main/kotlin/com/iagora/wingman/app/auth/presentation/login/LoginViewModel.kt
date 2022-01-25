package com.iagora.wingman.app.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.app.auth.domain.usecase.IRequestOTP
import com.iagora.wingman.core.presentation.util.UiEvent
import com.iagora.wingman.core.util.Error
import com.iagora.wingman.core.util.Event
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.UiText
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class LoginViewModel(private val requestOTP: IRequestOTP) : ViewModel() {

    private val _eventFLow = MutableSharedFlow<Event>()
    val eventFlow = _eventFLow.asSharedFlow()

    private val _loginState = MutableSharedFlow<Boolean>()
    val loginState = _loginState.asSharedFlow()

    private val _phoneNumberError = MutableSharedFlow<Error?>()
    val phoneNumberError = _phoneNumberError.asSharedFlow()


    fun requestLogin(phoneNumber: String) {
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
                    _eventFLow.emit(UiEvent.OnLogin)
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
            Timber.e("result ${request.result}")
        }
    }
}

