package com.iagora.wingman.app.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.app.auth.domain.usecase.IRequestLogin
import com.iagora.wingman.core.util.Event
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.UiEvent
import com.iagora.wingman.core.util.UiText
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val requestAuthLogin: IRequestLogin) : ViewModel() {

    private val _eventFLow = MutableSharedFlow<Event>()
    val eventFlow = _eventFLow.asSharedFlow()

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()


    fun requestLogin(phoneNumber: String, password: String) {
        viewModelScope.launch {
            _loginState.value = loginState.value.copy(isLoading = true)
            val request = requestAuthLogin(phoneNumber, password)
            _loginState.value = loginState.value.copy(isLoading = false)
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
        }
    }
}

