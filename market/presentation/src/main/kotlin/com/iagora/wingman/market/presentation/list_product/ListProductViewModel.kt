package com.iagora.wingman.market.presentation.list_product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.UiText
import com.iagora.wingman.market.domain.usecase.IGetListProduct
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class ListProductViewModel(
    idMarket: String,
    private val getListProduct: IGetListProduct
) : ViewModel() {
    private val _listProduct = MutableStateFlow(ListProductState())
    val listProduct = _listProduct.asStateFlow()


    init {
        loadListProduct(idMarket)
    }

    private fun loadListProduct(idMarket: String) {
        viewModelScope.launch {
            when (val result = getListProduct(idMarket)) {
                is Resource.Success -> {
                    _listProduct.emit(listProduct.value.copy(isLoading = false, data = result.data))
                }

                is Resource.Error -> {
                    _listProduct.emit(listProduct.value.copy(isLoading = false))
                    Timber.e("ERROR", (result.uiText ?: UiText.unknownError()).toString())
                }
            }
        }
    }
}