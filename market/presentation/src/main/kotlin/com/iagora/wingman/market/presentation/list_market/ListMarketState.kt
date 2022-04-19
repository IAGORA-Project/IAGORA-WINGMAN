package com.iagora.wingman.market.presentation.list_market

import com.iagora.wingman.market.helper.model.response.ListMarket

data class ListMarketState(
    val isLoading: Boolean = true,
    val data: ListMarket? = null
)