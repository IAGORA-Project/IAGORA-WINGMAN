package com.iagora.wingman.core.di.component

import com.iagora.wingman.core.data.session.SessionManager
import org.koin.dsl.module

val sharedPrefModule = module {
    single {
        SessionManager(get())
    }

}