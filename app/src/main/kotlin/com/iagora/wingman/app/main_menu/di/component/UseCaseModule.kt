package com.iagora.wingman.app.main_menu.di.component

import com.iagora.wingman.app.main_menu.data.usecase.GetWingmanInfo
import com.iagora.wingman.app.main_menu.domain.usecase.IGetWingmanInfo
import com.iagora.wingman.core.util.Constants.KEY_SHARED_WINGMAN
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCaseModule = module {
    single<IGetWingmanInfo> {
        GetWingmanInfo(get(), get(named(KEY_SHARED_WINGMAN)))
    }
}