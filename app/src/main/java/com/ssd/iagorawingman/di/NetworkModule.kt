package com.ssd.iagorawingman.di

import com.ssd.iagorawingman.data.source.remote.network.NetworkService
import org.koin.dsl.module

val networkModule = module {
    single { NetworkService() }
}