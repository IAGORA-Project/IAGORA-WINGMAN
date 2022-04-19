package com.iagora.wingman.core.di

import com.iagora.wingman.core.di.component.*

val coreModule =
    listOf(
        keyModule,
        sharedPrefModule,
        networkModule,
        repositoryModule,
        useCaseModule,
    )
