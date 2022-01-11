package com.iagora.wingman.market.di

import com.iagora.wingman.market.di.component.repositoryModule
import com.iagora.wingman.market.di.component.useCaseModule
import com.iagora.wingman.market.di.component.viewModelModule
import org.koin.core.context.loadKoinModules

val marketModule = loadKoinModules(
    listOf(
        viewModelModule,
        useCaseModule,
        repositoryModule,
        apiModule
    ))