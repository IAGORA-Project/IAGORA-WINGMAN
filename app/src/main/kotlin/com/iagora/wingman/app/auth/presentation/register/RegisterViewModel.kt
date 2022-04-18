package com.iagora.wingman.app.auth.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.app.auth.data.remote.request.RegistWingmanDetailReq
import com.iagora.wingman.app.auth.data.remote.request.RegistrationReq
import com.iagora.wingman.app.auth.domain.model.PersonalInfo
import com.iagora.wingman.app.auth.domain.usecase.*
import com.iagora.wingman.app.auth.presentation.on_boarding.OnBoardingPage
import com.iagora.wingman.core.data.session.SessionManager
import com.iagora.wingman.core.domain.util.KEYPref
import com.iagora.wingman.core.presentation.util.UiEvent
import com.iagora.wingman.core.util.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class RegisterViewModel(
    private val requestOTP: IRequestOTP,
    private val verifyOTP: IVerifyOTP,
    private val accessToken: IAccessToken,
    private val regComplate: IRegisterComplate,
    private val regDetail: IRegisterDetail,
    private val setPersonalInfo: ISetPersonalInfo,
    private val sessionManager: SessionManager
) : ViewModel() {
    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.OnSetPhoneOtp)
    val registerState = _registerState.asStateFlow()

    private val _onBoardingPage = MutableStateFlow<OnBoardingPage>(OnBoardingPage.LoginPage)
    val onBoardingpage = _onBoardingPage.asStateFlow()

    private val _eventFLow = MutableSharedFlow<Event>()
    val eventFlow = _eventFLow.asSharedFlow()

    private val _eventSuccessFLow = MutableSharedFlow<Event>()
    val eventSuccesFlow = _eventSuccessFLow.asSharedFlow()

    private val _registerDetailSuccess = MutableLiveData<String>()
    val registerDetailSuccess: LiveData<String> = _registerDetailSuccess

    private val _fullNameError = MutableSharedFlow<Error>()
    val fullNameError = _fullNameError.asSharedFlow()

    private val _addressError = MutableSharedFlow<Error>()
    val addressError = _addressError.asSharedFlow()

    private val _emailError = MutableSharedFlow<Error>()
    val emailError = _emailError.asSharedFlow()

    fun toPersonalInfo() {
        _registerState.value = RegisterState.OnSetPersonalInfo
    }

    fun toDocument() {
        _registerState.value = RegisterState.OnSetDocument
    }

    fun toSeNumbertOtp() {
        _registerState.value = RegisterState.OnSetPhoneOtp
    }

    fun toPreview(){
        _registerState.value = RegisterState.OnPreview
    }

    fun parsingErrorMessage(msg: String){
        viewModelScope.launch {
            _eventFLow.emit(
                UiEvent.CreateMessage(
                    uiText = UiText.DynamicString(msg) ?: UiText.unknownError()
                )
            )

        }
    }

    fun setPhoneNumber(phoneNumber: String) {
        viewModelScope.launch {
            val request = requestOTP(phoneNumber)
            when (request.result) {
                is Resource.SuccessMessage -> {
                    _eventSuccessFLow.emit(
                        UiEvent.CreateMessage(
                            request.result.uiText ?: UiText.unknownError()
                        )
                    )
                    _registerState.emit(RegisterState.OnSetVerifOtp)
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
                    _registerState.emit(RegisterState.OnSetPersonalInfo)
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

    fun registerDetail(register: RegistrationReq) {
        viewModelScope.launch {
            when (val request = regDetail(register)) {
                is Resource.SuccessMessage -> {
                    _eventSuccessFLow.emit(
                        UiEvent.CreateMessage(request.uiText ?: UiText.unknownError())
                    )
                    _registerDetailSuccess.postValue(request.uiText.toString())

                }
                is Resource.Error -> {
                    _eventFLow.emit(
                        UiEvent.CreateMessage(
                            request.uiText ?: UiText.unknownError()
                        )
                    )
                }

                else -> {}
            }
        }
    }

    fun registerComplete(
        ktp: MultipartBody.Part,
        skck: MultipartBody.Part,
        map: Map<String, RequestBody>
    ) {
        viewModelScope.launch {
            when (val request = regComplate(ktp, skck, map)) {
                is Resource.SuccessMessage -> {
                    _onBoardingPage.emit(OnBoardingPage.MainPage)
                    _eventSuccessFLow.emit(
                        UiEvent.CreateMessage(request.uiText ?: UiText.unknownError())
                    )
                }
                is Resource.Error -> {
                    _eventFLow.emit(
                        UiEvent.CreateMessage(
                            request.uiText ?: UiText.unknownError()
                        )
                    )
                }
            }
        }
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
                _registerState.value = RegisterState.OnSetDocument
            }
        }
    }
}