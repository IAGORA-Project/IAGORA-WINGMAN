package com.iagora.wingman.receive_order.di

import com.iagora.wingman.receive_order.di.components.apiModule
import com.iagora.wingman.receive_order.di.components.repositoryModule
import com.iagora.wingman.receive_order.di.components.useCaseModule
import com.iagora.wingman.receive_order.di.components.viewModelModule

val receiveOrderModule = listOf(
    apiModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)