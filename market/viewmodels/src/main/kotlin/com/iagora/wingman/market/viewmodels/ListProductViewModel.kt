package com.iagora.wingman.market.viewmodels

import androidx.lifecycle.viewModelScope
import com.iagora.wingman.commons.ui.base.BaseViewModel
import com.iagora.wingman.helper.Resource
import com.iagora.wingman.market.core.domain.usecase.MarketUseCase
import com.iagora.wingman.market.helper.model.response.ListProduct
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListProductViewModel(
    private val useCase: MarketUseCase
) : BaseViewModel<ListProduct>() {

    private val _vmGetListProductMarket: MutableSharedFlow<Resource<ListProduct>> =
        MutableSharedFlow()

    val vmGetListProductMarket = _vmGetListProductMarket.asSharedFlow()

    fun setIdMarket(idMarket: String) = viewModelScope.launch {
        useCase.getListProduct(idMarket).collectLatest {
            _vmGetListProductMarket.emit(it)
        }
    }
}