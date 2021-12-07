package com.iagora.wingman.receive_order.core.di

import com.iagora.wingman.receive_order.core.domain.usecase.ReceiveOrderInteractor
import com.iagora.wingman.receive_order.core.domain.usecase.ReceiveOrderUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<ReceiveOrderUseCase> {
        ReceiveOrderInteractor(get())
    }
}