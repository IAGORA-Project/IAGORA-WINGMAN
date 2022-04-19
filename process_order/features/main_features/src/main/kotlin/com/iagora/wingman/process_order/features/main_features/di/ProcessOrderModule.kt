package com.iagora.wingman.process_order.features.main_features.di

import com.iagora.wingman.process_order.core.di.apiModule
import com.iagora.wingman.process_order.core.di.dataSourceModule
import com.iagora.wingman.process_order.core.di.repositoryModule
import com.iagora.wingman.process_order.core.di.useCaseModule
import com.iagora.wingman.process_order.viewmodels.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module


private val viewModelModule = module {
    viewModel { ConfirmationViewModel(get()) }
    viewModel { ConfirmedViewModel(get()) }
    viewModel { PaymentViewModel(get()) }
    viewModel {
        ConfirmationDetailViewModel(
            get())
    }
    viewModel {
        ConfirmedDetailViewModel(get())
    }

    viewModel {
        ProcessOrderViewModel()
    }
}


val processOrderModule =
    loadKoinModules(
        listOf(
            viewModelModule,
            useCaseModule,
            repositoryModule,
            dataSourceModule,
            apiModule
        ))