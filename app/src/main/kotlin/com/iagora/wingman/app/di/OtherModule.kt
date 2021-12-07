package com.iagora.wingman.app.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module


val otherModule = module {
    factory { provideCoroutineScope() }
}

private fun provideCoroutineScope(): CoroutineScope =
    CoroutineScope(SupervisorJob() + Dispatchers.Default)
