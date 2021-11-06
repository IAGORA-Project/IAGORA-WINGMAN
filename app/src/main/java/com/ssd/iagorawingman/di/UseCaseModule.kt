package com.ssd.iagorawingman.di

import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.usecase.ProcessOrderOrderInteractor
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.usecase.ProcessOrderUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<ProcessOrderUseCase> { ProcessOrderOrderInteractor(get()) }
}