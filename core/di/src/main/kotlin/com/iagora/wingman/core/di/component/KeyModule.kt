package com.iagora.wingman.core.di.component




import com.iagora.wingman.core.di.BuildConfig
import com.iagora.wingman.core.util.Constants.BASE_URL
import com.iagora.wingman.core.util.Constants.KEY_CRYPT_AUTH
import com.iagora.wingman.core.util.Constants.KEY_SHARED_AUTH
import com.iagora.wingman.core.util.Constants.KEY_SHARED_DT
import com.iagora.wingman.core.util.Constants.KEY_SHARED_WINGMAN

import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val keyModule = module {
    single(named(BASE_URL)) {
        provideBaseURL()
    }

    single(named(KEY_SHARED_AUTH)) {
        provideKeySharedPrefAuth()
    }

    single(named(KEY_CRYPT_AUTH)) {
        provideKeyCryptAuth()
    }

    single(named(KEY_SHARED_DT)) {
        provideKeySharedPrefDT()
    }

    single(named(KEY_SHARED_WINGMAN)) {
        provideKeySharedWingman()
    }
}

private fun provideKeyCryptAuth() = BuildConfig.KEY_CRYPTO_AUTH
private fun provideKeySharedPrefDT() = BuildConfig.KEY_SHARED_PREFERENCE_DT
private fun provideBaseURL() = BuildConfig.BASE_URL
private fun provideKeySharedPrefAuth() = BuildConfig.KEY_SHARED_PREFERENCE_AUTH
private fun provideKeySharedWingman() = BuildConfig.KEY_SHARED_PREFERENCE_WINGMAN_INFO