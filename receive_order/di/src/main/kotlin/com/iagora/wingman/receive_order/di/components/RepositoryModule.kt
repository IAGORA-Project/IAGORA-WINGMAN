package com.iagora.wingman.receive_order.di.components

import com.iagora.wingman.receive_order.data.repository.ReceiveOrderRepository
import com.iagora.wingman.receive_order.domain.repository.IReceiveOrderRepository
import org.koin.dsl.module

internal val repositoryModule = module {
    single<IReceiveOrderRepository> { ReceiveOrderRepository(get()) }
}

