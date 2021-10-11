package com.ssd.iagorawingman.di

import com.ssd.iagorawingman.data.source.remote.api_handle.auth.AuthApi
import com.ssd.iagorawingman.data.source.remote.api_handle.pasar.PasarApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { provideAuthApi(get()) }
    single { providePasarApi(get()) }
}

private fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
private fun providePasarApi(retrofit: Retrofit): PasarApi = retrofit.create(PasarApi::class.java)
