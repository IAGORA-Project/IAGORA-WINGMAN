package com.iagora.wingman.market.di

import com.iagora.wingman.market.data.remote.MarketApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module { single { provideMarketApi(get()) } }

private fun provideMarketApi(retrofit: Retrofit): MarketApi =
    retrofit.create(MarketApi::class.java)