package com.iagora.wingman.app.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.app.auth.domain.model.PersonalInfo
import com.iagora.wingman.app.auth.domain.usecase.ISetPersonalInfo
import com.iagora.wingman.core.util.Error
import com.iagora.wingman.core.util.Event
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val setPersonalInfo: ISetPersonalInfo,
) : ViewModel() {


    private val _eventFLow = MutableSharedFlow<Event>()
    val eventFlow = _eventFLow.asSharedFlow()

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.OnSetPersonalInfo)
    val registerState = _registerState.asStateFlow()

    private val _dataInfo = MutableStateFlow<PersonalInfo?>(null)
    val dataInfo = _dataInfo.asStateFlow()

    private val _fullNameError = MutableSharedFlow<Error>()
    val fullNameError = _fullNameError.asSharedFlow()

    private val _addressError = MutableSharedFlow<Error>()
    val addressError = _addressError.asSharedFlow()

    private val _emailError = MutableSharedFlow<Error>()
    val emailError = _emailError.asSharedFlow()


    fun toPersonalInfo() {
        _registerState.value = RegisterState.OnSetPersonalInfo
    }

    fun toDocument(){
        _registerState.value = RegisterState.OnSetDocument
    }

    fun preview() {

    }

    fun document() {

    }

    fun personalInfo(
        fullName: String,
        email: String,
        address: String,
    ) {
        val set = setPersonalInfo(fullName, email, address)

        viewModelScope.launch {
            if (set.fullNameError != null) {
                _fullNameError.emit(set.fullNameError)
            }
            if (set.addressError != null) {
                _addressError.emit(set.addressError)
            }
            if (set.emailError != null) {
                _emailError.emit(set.emailError)
            }

            if (set.result != null) {
                _dataInfo.emit(set.result)
                _registerState.value = RegisterState.OnSetDocument
            }
        }
    }
}