package com.iagora.wingman.app.main_menu.di

import com.iagora.wingman.app.main_menu.di.component.apiModule
import com.iagora.wingman.app.main_menu.di.component.repositoryModule
import com.iagora.wingman.app.main_menu.di.component.useCaseModule
import com.iagora.wingman.app.main_menu.di.component.viewModelModule

val mainModule = listOf(
    apiModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)