package com.iagora.wingman.app.auth.di

import com.iagora.wingman.app.auth.di.component.apiModule
import com.iagora.wingman.app.auth.di.component.repositoryModule
import com.iagora.wingman.app.auth.di.component.useCaseModule
import com.iagora.wingman.app.auth.di.component.viewModelModule

val authModule = listOf(
    apiModule,
    repositoryModule,
    useCaseModule,
    viewModelModule,
)