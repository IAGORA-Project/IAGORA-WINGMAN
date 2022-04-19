package com.iagora.wingman.market.di.component

import com.iagora.wingman.core.data.session.SessionManager
import org.koin.dsl.module

val sharedPrefModule = module {
    single {
        SessionManager(get())
    }

}