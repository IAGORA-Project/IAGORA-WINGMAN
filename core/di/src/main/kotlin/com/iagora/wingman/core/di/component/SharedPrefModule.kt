package com.iagora.wingman.core.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.iagora.wingman.core.domain.session.SessionManager
import com.iagora.wingman.core.util.Constants.SHARED_PREF_NAME
import org.koin.dsl.module

val sharedPrefModule = module {
    single {
        SessionManager(get())
    }
}
