package com.ssd.iagorawingman.di

import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.source.ProcessOrderRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { ProcessOrderRemoteDataSource(get(), get()) }
}