package com.ssd.iagorawingman.di

import com.ssd.iagora_user.data.source.local.shared_view_model.SharedViewModel
import com.ssd.iagorawingman.ui.auth.AuthViewModel
import com.ssd.iagorawingman.ui.pasar.PasarViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    single { SharedViewModel() }
    viewModel { AuthViewModel( get(), get(), get() ) }
    viewModel { PasarViewModel(  get(), get() ) }
}