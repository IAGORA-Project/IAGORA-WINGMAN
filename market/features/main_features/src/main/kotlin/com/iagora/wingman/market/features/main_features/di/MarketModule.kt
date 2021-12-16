package com.iagora.wingman.market.features.main_features.di

import com.iagora.wingman.market.core.di.apiModule
import com.iagora.wingman.market.core.di.dataSourceModule
import com.iagora.wingman.market.core.di.repositoryModule
import com.iagora.wingman.market.core.di.useCaseModule
import com.iagora.wingman.market.viewmodels.ListMarketViewModel
import com.iagora.wingman.market.viewmodels.ListProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel { ListMarketViewModel(get()) }
    viewModel { ListProductViewModel(get()) }
}

val marketModule = loadKoinModules(
    listOf(
        viewModelModule,
        useCaseModule,
        repositoryModule,
        dataSourceModule,
        apiModule
    ))