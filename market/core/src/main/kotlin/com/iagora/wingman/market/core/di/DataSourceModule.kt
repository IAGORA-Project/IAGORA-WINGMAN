package com.iagora.wingman.market.core.di

import com.iagora.wingman.market.core.source.remote.MarketRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single {
        MarketRemoteDataSource(get(),
            get())
    }
}