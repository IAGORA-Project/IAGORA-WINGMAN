package com.iagora.wingman.receive_order.di.components

import com.iagora.wingman.receive_order.data.remote.ReceiveOrderApi
import org.koin.dsl.module
import retrofit2.Retrofit

internal val apiModule = module {
    single { provideReceiveOrderApi(get()) }
}

private fun provideReceiveOrderApi(retrofit: Retrofit): ReceiveOrderApi =
    retrofit.create(ReceiveOrderApi::class.java)