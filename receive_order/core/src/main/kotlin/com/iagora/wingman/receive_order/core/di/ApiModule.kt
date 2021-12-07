package com.iagora.wingman.receive_order.core.di

import com.iagora.wingman.receive_order.core.ReceiveOrderApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module { single { provideReceiveOrderApi(get()) } }

private fun provideReceiveOrderApi(retrofit: Retrofit): ReceiveOrderApi =
    retrofit.create(ReceiveOrderApi::class.java)