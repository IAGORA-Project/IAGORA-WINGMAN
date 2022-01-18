package com.iagora.wingman.market.di.component

import com.iagora.wingman.market.presentation.add_product.AddProductViewModel
import com.iagora.wingman.market.presentation.list_market.ListMarketViewModel
import com.iagora.wingman.market.presentation.list_product.ListProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel { ListMarketViewModel(get()) }
    viewModel { param -> ListProductViewModel(param.get(), get()) }
    viewModel { AddProductViewModel(get()) }
}