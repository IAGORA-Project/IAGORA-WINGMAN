package com.iagora.wingman.core.di

import com.iagora.wingman.core.di.component.keyModule
import com.iagora.wingman.core.di.component.networkModule
import com.iagora.wingman.core.di.component.sharedPrefModule
import com.iagora.wingman.core.di.component.useCaseModule

val coreModule =
    listOf(
        keyModule,
        sharedPrefModule,
        networkModule,
        useCaseModule,
    )
