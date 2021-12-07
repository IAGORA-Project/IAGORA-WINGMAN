package com.iagora.wingman.app.di

import com.iagora.wingman.core.source.remote.api_handle.auth.AuthApi
import com.iagora.wingman.core.source.remote.api_handle.main_menu.MainMenuApi
import com.iagora.wingman.core.source.remote.api_handle.pasar.PasarApi
import com.iagora.wingman.receive_order.core.ReceiveOrderApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { provideMainMenuApi(get()) }
    single { provideAuthApi(get()) }
    single { providePasarApi(get()) }
    single { provideReceiveOrderApi(get()) }

}

private fun provideMainMenuApi(retrofit: Retrofit): MainMenuApi =
    retrofit.create(MainMenuApi::class.java)
private fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
private fun providePasarApi(retrofit: Retrofit): PasarApi = retrofit.create(PasarApi::class.java)
private fun provideReceiveOrderApi(retrofit: Retrofit): com.iagora.wingman.receive_order.core.ReceiveOrderApi =
    retrofit.create(com.iagora.wingman.receive_order.core.ReceiveOrderApi::class.java)

