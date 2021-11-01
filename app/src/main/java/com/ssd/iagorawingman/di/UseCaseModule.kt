package com.ssd.iagorawingman.di

import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process.domain.usecase.OnProcessInteractor
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process.domain.usecase.OnProcessUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<OnProcessUseCase> { OnProcessInteractor(get()) }
}