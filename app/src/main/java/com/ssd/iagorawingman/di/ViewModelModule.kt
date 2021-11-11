package com.ssd.iagorawingman.di

import com.ssd.iagora_user.data.source.local.shared_view_model.SharedViewModel
import com.ssd.iagorawingman.ui.auth.AuthViewModel
import com.ssd.iagorawingman.ui.main_menu.MainViewModel
import com.ssd.iagorawingman.ui.pasar.PasarViewModel
import com.ssd.iagorawingman.ui.process_order.ProcessOrderViewModel
import com.ssd.iagorawingman.ui.process_order.on_process.waiting_list.WaitingListViewModel
import com.ssd.iagorawingman.ui.process_order.on_process.waiting_list.detail.confirmation.ConfirmationViewModel
import com.ssd.iagorawingman.ui.receive_order.ReceiveOrderViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    single { SharedViewModel() }
    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { AuthViewModel(get(), get(), get()) }
    viewModel { PasarViewModel(get(), get()) }
    viewModel { ReceiveOrderViewModel(get(), get()) }
    viewModel { WaitingListViewModel(get()) }
    viewModel { ConfirmationViewModel(get()) }
    viewModel { ProcessOrderViewModel() }
}