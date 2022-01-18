package com.iagora.wingman.market.di

import com.iagora.wingman.market.di.component.apiModule
import com.iagora.wingman.market.di.component.repositoryModule
import com.iagora.wingman.market.di.component.useCaseModule
import com.iagora.wingman.market.di.component.viewModelModule

val marketModule =
    listOf(
        viewModelModule,
        useCaseModule,
        repositoryModule,
        apiModule
    )