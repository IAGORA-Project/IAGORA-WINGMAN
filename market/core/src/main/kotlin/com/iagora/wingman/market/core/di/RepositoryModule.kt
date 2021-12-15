package com.iagora.wingman.market.core.di

import com.iagora.wingman.market.core.MarketRepository
import com.iagora.wingman.market.core.domain.repository.IMarketRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IMarketRepository> {
        MarketRepository(get())
    }
}