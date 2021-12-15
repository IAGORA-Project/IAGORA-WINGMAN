package com.iagora.wingman.market.core.di

import com.iagora.wingman.market.core.domain.usecase.MarketInteractor
import com.iagora.wingman.market.core.domain.usecase.MarketUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<MarketUseCase> {
        MarketInteractor(get())
    }
}