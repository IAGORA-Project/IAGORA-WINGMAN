package com.iagora.wingman.app.auth.di.component

import com.iagora.wingman.app.auth.data.repository.AuthRepository
import com.iagora.wingman.app.auth.domain.repository.IAuthRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IAuthRepository> {
        AuthRepository(
            get(),
            get()
        )
    }
}