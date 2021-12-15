package com.iagora.wingman.process_order.core.di

import com.iagora.wingman.process_order.core.ProcessOrderRepository
import com.iagora.wingman.process_order.core.domain.repository.IProcessOrderRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IProcessOrderRepository> {
        ProcessOrderRepository(get())
    }
}