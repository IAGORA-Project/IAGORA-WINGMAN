package com.iagora.wingman.app.auth.di.component

import com.iagora.wingman.app.auth.data.repository.AuthRepository
import com.iagora.wingman.app.auth.domain.repository.IAuthRepository
import com.iagora.wingman.core.util.Constants.KEY_CRYPT_AUTH
import com.iagora.wingman.core.util.Constants.KEY_SHARED_AUTH
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<IAuthRepository> {
        AuthRepository(
            get(),
            get(),
            get(named(KEY_CRYPT_AUTH)),
            get(named(KEY_SHARED_AUTH))
        )
    }
}