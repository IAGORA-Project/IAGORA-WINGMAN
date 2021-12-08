package com.iagora.wingman.process_order.core.di

import com.iagora.wingman.process_order.core.source.remote.ProcessOrderRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single {
        ProcessOrderRemoteDataSource(get(),
            get())
    }
}