package com.iagora.wingman.core.di

import org.koin.dsl.module
import com.iagora.wingman.core.source.remote.network.NetworkService

val networkModule = module {
    single { NetworkService(get()) }
}