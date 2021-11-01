package com.ssd.iagorawingman.di

import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process.source.OnProcessRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { OnProcessRemoteDataSource(get()) }
}