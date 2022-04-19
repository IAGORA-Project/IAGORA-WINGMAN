package com.iagora.wingman.process_order.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ProcessOrderViewModel : ViewModel() {

    private val _posTab: MutableSharedFlow<Int> =
        MutableSharedFlow(1, 0, BufferOverflow.DROP_OLDEST)
    val posTab = _posTab.distinctUntilChanged()

    init {
        setPosTab(0)
    }

    fun setPosTab(pos: Int) = viewModelScope.launch {
        _posTab.emit(pos)
    }
}