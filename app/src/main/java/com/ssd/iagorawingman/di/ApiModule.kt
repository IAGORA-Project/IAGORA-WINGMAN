package com.ssd.iagorawingman.di

import com.ssd.iagorawingman.data.source.remote.api_handle.auth.AuthApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { provideAuthApi(get()) }
}

private fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
