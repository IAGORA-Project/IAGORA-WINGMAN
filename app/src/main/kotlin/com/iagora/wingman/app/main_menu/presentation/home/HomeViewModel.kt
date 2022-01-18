package com.iagora.wingman.app.main_menu.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.app.main_menu.domain.usecase.IGetWingmanInfo
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(
    private val getWingmanInfo: IGetWingmanInfo,
) : ViewModel() {
    private val _dataWingman = MutableStateFlow(WingmanState())
    val dataWingman = _dataWingman.asStateFlow()

    init {
        loadDataWingman()
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