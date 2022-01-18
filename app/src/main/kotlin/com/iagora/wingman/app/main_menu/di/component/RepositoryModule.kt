package com.iagora.wingman.app.main_menu.di.component

import com.iagora.wingman.app.main_menu.data.repository.MainRepository
import com.iagora.wingman.app.main_menu.domain.repository.IMainRepository
import com.iagora.wingman.core.util.Constants.KEY_CRYPT_AUTH
import com.iagora.wingman.core.util.Constants.KEY_SHARED_WINGMAN
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<IMainRepository> {
        MainRepository(
            get(),
            get(),
            get(),
            get(named(KEY_SHARED_WINGMAN)),
            get(named(KEY_CRYPT_AUTH))
        )
    }
}