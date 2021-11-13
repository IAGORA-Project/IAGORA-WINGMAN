package com.ssd.iagorawingman.di

import com.ssd.iagora_user.data.source.local.shared_view_model.SharedViewModel
import com.ssd.iagorawingman.ui.auth.AuthViewModel
import com.ssd.iagorawingman.ui.main_menu.MainViewModel
import com.ssd.iagorawingman.ui.pasar.PasarViewModel
import com.ssd.iagorawingman.ui.process_order.ProcessOrderViewModel
import com.ssd.iagorawingman.ui.process_order.waiting_list.confirmation.ConfirmationViewModel
import com.ssd.iagorawingman.ui.process_order.waiting_list.confirmation.detail.ConfirmationDetailViewModel
import com.ssd.iagorawingman.ui.process_order.waiting_list.confirmed.ConfirmedViewModel
import com.ssd.iagorawingman.ui.process_order.waiting_list.confirmed.detail.ConfirmedDetailViewModel
import com.ssd.iagorawingman.ui.process_order.waiting_list.payment.PaymentViewModel
import com.ssd.iagorawingman.ui.receive_order.ReceiveOrderViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    single { SharedViewModel() }
    viewModel { MainViewModel(get(), get()) }
    viewModel { AuthViewModel(get(), get(), get()) }
    viewModel { PasarViewModel(get()) }
    viewModel { ReceiveOrderViewModel(get()) }
    viewModel { ConfirmationViewModel(get()) }
    viewModel { ConfirmedViewModel(get()) }
    viewModel { PaymentViewModel(get()) }
    viewModel { ConfirmationDetailViewModel(get()) }
    viewModel { ConfirmedDetailViewModel(get()) }
    viewModel { ProcessOrderViewModel() }
}