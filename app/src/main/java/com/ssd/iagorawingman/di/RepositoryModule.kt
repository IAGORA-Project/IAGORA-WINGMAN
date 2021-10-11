package com.ssd.iagorawingman.di

import com.google.gson.Gson
import com.ssd.iagorawingman.data.source.local.shared_handle.auth.SharedAuthRepository
import com.ssd.iagorawingman.data.source.local.shared_handle.device_token.SharedDeviceTokenRepository
import com.ssd.iagorawingman.data.source.remote.api_handle.auth.AuthReposiroty
import com.ssd.iagorawingman.data.source.remote.api_handle.pasar.PasarRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { Gson() }
    factory { SharedAuthRepository( get(),get() ) }
    factory { SharedDeviceTokenRepository( get(),get() ) }
    factory { AuthReposiroty( get() ) }
    factory { PasarRepository( get() ) }
}