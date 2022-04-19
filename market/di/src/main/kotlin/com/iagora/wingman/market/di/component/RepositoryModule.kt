package com.iagora.wingman.market.di.component

import com.iagora.wingman.market.data.repository.MarketRepository
import com.iagora.wingman.market.domain.repository.IMarketRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IMarketRepository> {
        MarketRepository(get(), get())
    }
}