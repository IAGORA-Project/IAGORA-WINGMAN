package com.iagora.wingman.app.di

import com.iagora.wingman.app.ui.auth.AuthViewModel
import com.iagora.wingman.app.ui.main_menu.MainViewModel
import com.iagora.wingman.app.ui.pasar.PasarViewModel
import com.iagora.wingman.core.source.local.shared_view_model.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    single { SharedViewModel() }
    viewModel { MainViewModel(get(), get()) }
    viewModel { AuthViewModel(get(), get(), get()) }
    viewModel { PasarViewModel(get()) }
}