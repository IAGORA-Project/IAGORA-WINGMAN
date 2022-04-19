package com.iagora.wingman.app.main_menu.di.component


import com.iagora.wingman.app.main_menu.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get(), get()) }
}