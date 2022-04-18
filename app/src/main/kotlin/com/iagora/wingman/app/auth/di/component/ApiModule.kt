package com.iagora.wingman.app.auth.di.component

import com.iagora.wingman.app.auth.data.remote.AuthApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single {
        provideAuthApi(get())
    }
}

private fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)