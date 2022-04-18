package com.iagora.wingman.market.di

import com.iagora.wingman.market.di.component.*
import com.iagora.wingman.market.di.component.apiModule
import com.iagora.wingman.market.di.component.useCaseModule

val marketModule =
    listOf(
        sharedPrefModule,
        viewModelModule,
        useCaseModule,
        repositoryModule,
        apiModule
    )