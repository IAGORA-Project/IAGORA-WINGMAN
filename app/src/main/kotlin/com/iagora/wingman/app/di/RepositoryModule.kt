package com.iagora.wingman.app.di

import com.google.gson.Gson
import com.iagora.wingman.core.source.local.shared_handle.auth.SharedAuthRepository
import com.iagora.wingman.core.source.local.shared_handle.device_token.SharedDeviceTokenRepository
import com.iagora.wingman.core.source.local.shared_handle.wingman_info.SharedWingmanInfoRepository
import com.iagora.wingman.core.source.remote.api_handle.auth.AuthReposiroty
import com.iagora.wingman.core.source.remote.api_handle.main_menu.MainMenuRepository


import org.koin.dsl.module

val repositoryModule = module {
    single { Gson() }
    factory { SharedAuthRepository(get(), get()) }
    factory { SharedWingmanInfoRepository(get(), get()) }
    factory { SharedDeviceTokenRepository(get(), get()) }
    factory { MainMenuRepository(get()) }
    factory { AuthReposiroty(get()) }
}