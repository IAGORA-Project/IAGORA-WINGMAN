package com.iagora.wingman.receive_order.features.main_features.di

import com.iagora.wingman.receive_order.core.di.apiModule
import com.iagora.wingman.receive_order.core.di.dataSourceModule
import com.iagora.wingman.receive_order.core.di.repositoryModule
import com.iagora.wingman.receive_order.core.di.useCaseModule
import com.iagora.wingman.receive_order.features.main_features.ReceiveOrderViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel {
        ReceiveOrderViewModel(get())
    }
}

val receiveOrderModule = loadKoinModules(
    listOf(
        viewModelModule,
        useCaseModule,
        repositoryModule,
        dataSourceModule,
        apiModule
    ))