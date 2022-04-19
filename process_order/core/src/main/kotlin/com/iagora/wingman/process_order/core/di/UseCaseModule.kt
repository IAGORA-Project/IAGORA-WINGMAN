package com.iagora.wingman.process_order.core.di

import com.iagora.wingman.process_order.core.domain.usecase.ProcessOrderInteractor
import com.iagora.wingman.process_order.core.domain.usecase.ProcessOrderUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<ProcessOrderUseCase> {
        ProcessOrderInteractor(get())
    }
}