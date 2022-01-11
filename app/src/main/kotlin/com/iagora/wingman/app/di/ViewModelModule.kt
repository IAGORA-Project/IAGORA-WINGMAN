package com.iagora.wingman.app.di

import com.iagora.wingman.app.ui.auth.AuthViewModel
import com.iagora.wingman.app.ui.main_menu.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { AuthViewModel(get(), get(), get()) }
}