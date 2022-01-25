package com.iagora.wingman.core.di.component

import com.iagora.wingman.core.data.repository.CoreRepository
import com.iagora.wingman.core.domain.repository.ICoreRepository
import org.koin.dsl.module

internal val repositoryModule = module {
    single<ICoreRepository> {
        CoreRepository(get(), get(), get())
    }
}