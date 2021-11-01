package com.ssd.iagorawingman.ui.process_order.on_process

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ssd.iagorawingman.BuildConfig.KEY_SHARED_PREFERENCE_AUTH
import com.ssd.iagorawingman.data.source.local.shared_handle.auth.SharedAuthRepository
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process.domain.model.ListWaitingOnProcess
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process.domain.usecase.OnProcessUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OnProcessViewModel(
    useCase: OnProcessUseCase
) : ViewModel() {
    val vmGetAllListWaiting = useCase.getAllListWaiting().asLiveData()

    //    one time event
    private val _dataUI = MutableSharedFlow<ListWaitingOnProcess>()
    val dataUI = _dataUI.asSharedFlow()

    fun setDataUI(data: ListWaitingOnProcess) {
        viewModelScope.launch {
            _dataUI.emit(data)
        }
    }
}