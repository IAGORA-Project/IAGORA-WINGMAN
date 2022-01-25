package com.iagora.wingman.app.auth.di.component

import com.iagora.wingman.app.auth.presentation.login.LoginViewModel
import com.iagora.wingman.app.auth.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { SplashViewModel(get()) }
}