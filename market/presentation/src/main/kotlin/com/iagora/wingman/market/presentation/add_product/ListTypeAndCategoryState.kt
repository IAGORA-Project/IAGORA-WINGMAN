package com.iagora.wingman.market.presentation.add_product

import com.iagora.wingman.market.helper.model.response.ListTypeAndCategory

data class ListTypeAndCategoryState(
    val isLoading: Boolean = true,
    val data: ListTypeAndCategory? = null
)