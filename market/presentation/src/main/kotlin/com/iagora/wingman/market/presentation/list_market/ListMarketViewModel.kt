package com.iagora.wingman.market.presentation.list_market

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.UiText
import com.iagora.wingman.market.domain.usecase.IGetListMarket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListMarketViewModel(private val getListMarket: IGetListMarket) : ViewModel() {
    private val _listMarket = MutableStateFlow(ListMarketState())
    val listMarket = _listMarket.asStateFlow()

    init {
        loadListMarket()
    }


    private fun loadListMarket() {
        viewModelScope.launch {
            when (val result = getListMarket()) {
                is Resource.Success -> {
                    _listMarket.emit(listMarket.value.copy(isLoading = false, data = result.data))
                }

                is Resource.Error -> {
                    _listMarket.emit(listMarket.value.copy(isLoading = false))
                    Log.e("ERROR", (result.uiText ?: UiText.unknownError()).toString())
                }
            }
        }
    }

}