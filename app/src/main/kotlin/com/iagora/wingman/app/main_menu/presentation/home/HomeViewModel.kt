package com.iagora.wingman.app.main_menu.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.app.auth.domain.usecase.IAccessToken
import com.iagora.wingman.app.auth.domain.usecase.IRequestOTP
import com.iagora.wingman.app.auth.presentation.register.RegisterState
import com.iagora.wingman.app.main_menu.domain.usecase.IGetWingmanInfo
import com.iagora.wingman.core.data.session.SessionManager
import com.iagora.wingman.core.domain.util.KEYPref
import com.iagora.wingman.core.presentation.util.UiEvent
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(
    private val getWingmanInfo: IGetWingmanInfo,
    private val accessToken: IAccessToken,
    private val sessionManager: SessionManager

) : ViewModel() {
    private val _dataWingman = MutableStateFlow(WingmanState())
    val dataWingman = _dataWingman.asStateFlow()

    init {
        refreshAccesToken()
        loadDataWingman()
    }




    private fun refreshAccesToken() {
        viewModelScope.launch {
            val request = accessToken(sessionManager.getFromPreference(KEYPref.TOKEN))
            when (request.result) {
                is Resource.SuccessMessage -> {

                    Timber.d("getAccesToken: " + request.result.uiText)
//                    _eventSuccessFLow.emit(
//                        UiEvent.CreateMessage(request.result.uiText ?: UiText.unknownError())
//                    )
                }
                is Resource.Error -> {
//                    _eventFLow.emit(
//                        UiEvent.CreateMessage(
//                            request.result.uiText ?: UiText.unknownError()
//                        )
//                    )
                }
                else -> {}
            }
        }
    }

    private fun loadDataWingman() = viewModelScope.launch {
        when (val result = getWingmanInfo()) {
            is Resource.Success -> {
                _dataWingman.emit(
                    dataWingman.value.copy(
                        isLoading = false,
                        wingmanInfo = result.data
                    )
                )
            }
            is Resource.Error -> {
                _dataWingman.emit(dataWingman.value.copy(isLoading = false))
                Timber.e(({
                    result.uiText ?: UiText.unknownError()
                }).toString())
            }
        }
    }
}