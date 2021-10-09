package com.ssd.iagorawingman.di

import com.ssd.iagorawingman.ui.auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { AuthViewModel( get(), get(), get() ) }
}