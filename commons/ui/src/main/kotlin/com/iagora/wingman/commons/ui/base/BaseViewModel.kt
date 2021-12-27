package com.iagora.wingman.commons.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<T> : ViewModel() {
    private val _vmData: MutableStateFlow<T?> = MutableStateFlow(null)

    val vmData = _vmData.asStateFlow()

    fun setData(data: T?) {
        _vmData.value = data
    }
}