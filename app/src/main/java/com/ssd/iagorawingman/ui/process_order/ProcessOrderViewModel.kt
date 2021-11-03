package com.ssd.iagorawingman.ui.process_order

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.usecase.ProcessOrderUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProcessOrderViewModel(
    private val orderUseCase: ProcessOrderUseCase
) : ViewModel() {

    fun vmGetWaitingList() = orderUseCase.getAllListWaiting()

    init {
        setInitPositionTab()

    }


    private val _totalWaitingList: MutableStateFlow<Int> = MutableStateFlow(0)
    val totalWaitingList: StateFlow<Int> = _totalWaitingList

    fun setTotalWaitingList(total: Int) {
        _totalWaitingList.value = total
    }

    private val _initPositionTab: MutableSharedFlow<Int?> = MutableSharedFlow()
    val initPositionTab: SharedFlow<Int?> = _initPositionTab.asSharedFlow()


    fun setInitPositionTab(pos: Int? = null) {
        viewModelScope.launch {
            _initPositionTab.emit(pos)
        }
    }
}