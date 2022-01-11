package com.iagora.wingman.market.presentation.list_product

import com.iagora.wingman.market.helper.model.response.ListProduct

data class ListProductState(
    val isLoading: Boolean = true,
    val data: ListProduct? = null
)