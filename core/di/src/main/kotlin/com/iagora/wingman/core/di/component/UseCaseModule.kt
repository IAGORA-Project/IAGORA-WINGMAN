package com.iagora.wingman.core.di.component

import com.iagora.wingman.core.data.usecase.GetSESSID
import com.iagora.wingman.core.domain.usecase.IGetSESSID
import org.koin.dsl.module

val useCaseModule = module {
    single<IGetSESSID> {
        GetSESSID(get())
    }
}