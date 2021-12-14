package com.iagora.wingman.market.viewmodels

import com.iagora.wingman.commons.ui.base.BaseViewModel
import com.iagora.wingman.market.core.domain.usecase.MarketUseCase
import com.iagora.wingman.market.helper.model.response.ListMarket

class ListMarketViewModel(
    useCase: MarketUseCase,
) : BaseViewModel<ListMarket>() {
    val getListMarket = useCase.getListMarket()
}