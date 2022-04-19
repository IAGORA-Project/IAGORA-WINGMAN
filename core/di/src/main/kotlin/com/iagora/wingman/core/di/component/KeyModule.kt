package com.iagora.wingman.core.di.component




import com.iagora.wingman.core.di.BuildConfig
import com.iagora.wingman.core.util.Constants.BASE_URL
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val keyModule = module {
    single(named(BASE_URL)) {
        provideBaseURL()
    }
}

private fun provideBaseURL() = BuildConfig.BASE_URL