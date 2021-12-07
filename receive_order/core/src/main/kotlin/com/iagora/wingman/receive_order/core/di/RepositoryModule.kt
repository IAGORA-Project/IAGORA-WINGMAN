package com.iagora.wingman.receive_order.core.di

import com.iagora.wingman.receive_order.core.ReceiveOrderRepository
import com.iagora.wingman.receive_order.core.domain.repository.IReceiveOrderRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IReceiveOrderRepository> {
        ReceiveOrderRepository(get())
    }
}