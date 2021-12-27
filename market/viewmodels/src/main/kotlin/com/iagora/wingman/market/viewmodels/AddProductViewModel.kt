package com.iagora.wingman.market.viewmodels

import androidx.lifecycle.viewModelScope
import com.iagora.wingman.commons.ui.base.BaseViewModel
import com.iagora.wingman.helper.Resource
import com.iagora.wingman.market.core.domain.usecase.MarketUseCase
import com.iagora.wingman.market.helper.model.body.AddProduct
import com.iagora.wingman.market.helper.model.response.AddProductFeedBack
import com.iagora.wingman.market.helper.model.response.ListTypeAndCategory
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AddProductViewModel(private val useCase: MarketUseCase) :
    BaseViewModel<ListTypeAndCategory>() {

    val vmGetListTypeCategory = useCase.getListTypeAndCategory()

    private val _vmFeedBackAddProduct = MutableSharedFlow<Resource<AddProductFeedBack>>()
    val vmFeedBackAddProduct = _vmFeedBackAddProduct.asSharedFlow()

    fun vmPostAddProduct(addProduct: AddProduct) = viewModelScope.launch {
        useCase.postAddProduct(addProduct).collectLatest { res ->
            _vmFeedBackAddProduct.emit(res)
        }
    }
}