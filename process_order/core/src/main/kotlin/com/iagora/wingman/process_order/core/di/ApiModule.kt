package com.iagora.wingman.process_order.core.di

import com.iagora.wingman.process_order.core.ProcessOrderApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module { single { provideProcessOrderApi(get()) } }

private fun provideProcessOrderApi(retrofit: Retrofit): ProcessOrderApi =
    retrofit.create(ProcessOrderApi::class.java)







