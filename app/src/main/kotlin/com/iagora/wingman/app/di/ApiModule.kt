package com.iagora.wingman.app.di

import com.iagora.wingman.core.source.remote.api_handle.auth.AuthApi
import com.iagora.wingman.core.source.remote.api_handle.main_menu.MainMenuApi

import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { provideMainMenuApi(get()) }
    single { provideAuthApi(get()) }
}

private fun provideMainMenuApi(retrofit: Retrofit): MainMenuApi =
    retrofit.create(MainMenuApi::class.java)
private fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)



