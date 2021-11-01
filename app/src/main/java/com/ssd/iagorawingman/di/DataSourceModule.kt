package com.ssd.iagorawingman.di

import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process.source.OnProcessRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

val dataSourceModule = module {
    single { provideCoroutineScope() }
    single { OnProcessRemoteDataSource(get(), get()) }

}

private fun provideCoroutineScope(): CoroutineScope =
    CoroutineScope(SupervisorJob() + Dispatchers.Default)