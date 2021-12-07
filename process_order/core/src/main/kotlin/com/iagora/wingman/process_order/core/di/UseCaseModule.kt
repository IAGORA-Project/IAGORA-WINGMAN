package com.iagora.wingman.process_order.core.di

import com.iagora.wingman.process_order.core.domain.usecase.ProcessOrderOrderInteractor
import com.iagora.wingman.process_order.core.domain.usecase.ProcessOrderUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<ProcessOrderUseCase> {
        ProcessOrderOrderInteractor(get())
    }
}