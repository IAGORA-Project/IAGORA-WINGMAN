package com.iagora.wingman.commons.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

abstract class BaseViewModel<T> : ViewModel() {
    private val _vmData: MutableSharedFlow<T?> =
        MutableSharedFlow(1, 0, BufferOverflow.DROP_OLDEST)
    val vmData = _vmData.distinctUntilChanged()

    init {
        setData(null)
    }

    fun setData(data: T?) = viewModelScope.launch {
        _vmData.emit(data)
    }
}