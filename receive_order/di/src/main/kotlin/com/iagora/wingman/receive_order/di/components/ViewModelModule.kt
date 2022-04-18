package com.iagora.wingman.receive_order.di.components

import com.iagora.wingman.receive_order.presentation.ReceiveOrderViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel {
        ReceiveOrderViewModel(get(), get())
    }
}