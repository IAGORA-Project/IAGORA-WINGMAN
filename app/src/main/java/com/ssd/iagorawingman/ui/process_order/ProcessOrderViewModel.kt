package com.ssd.iagorawingman.ui.process_order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.usecase.ProcessOrderUseCase
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProcessOrderViewModel: ViewModel() {

    init {
        setInitPositionTab()
    }

    private val _initPositionTab: MutableSharedFlow<Int?> = MutableSharedFlow()
    val initPositionTab: SharedFlow<Int?> = _initPositionTab.asSharedFlow()


    fun setInitPositionTab(pos: Int? = null) {
        viewModelScope.launch {
            _initPositionTab.emit(pos)
        }
    }
}