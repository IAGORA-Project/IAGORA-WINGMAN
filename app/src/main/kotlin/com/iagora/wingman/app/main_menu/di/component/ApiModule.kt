package com.iagora.wingman.app.main_menu.di.component

import com.iagora.wingman.app.auth.data.remote.AuthApi
import com.iagora.wingman.app.main_menu.data.remote.MainApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single {
        provideMainApi(get())
    }
}

private fun provideMainApi(retrofit: Retrofit): MainApi = retrofit.create(MainApi::class.java)