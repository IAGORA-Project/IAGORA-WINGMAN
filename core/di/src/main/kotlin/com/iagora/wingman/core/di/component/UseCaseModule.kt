package com.iagora.wingman.core.di.component

import com.iagora.wingman.core.data.usecase.DeleteDataFromSharedPref
import com.iagora.wingman.core.data.usecase.GetAuthToken
import com.iagora.wingman.core.data.usecase.GetSESSID
import com.iagora.wingman.core.domain.usecase.IDeleteDataFromSharedPref
import com.iagora.wingman.core.domain.usecase.IGetAuthToken
import com.iagora.wingman.core.domain.usecase.IGetSESSID
import com.iagora.wingman.core.util.Constants.KEY_CRYPT_AUTH
import com.iagora.wingman.core.util.Constants.KEY_SHARED_AUTH
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCaseModule = module {
    single<IDeleteDataFromSharedPref> {
        DeleteDataFromSharedPref(get())
    }

    single<IGetAuthToken> {
        GetAuthToken(
            get(),
            get(named(KEY_SHARED_AUTH)),
            get(named(KEY_CRYPT_AUTH))
        )
    }

    single<IGetSESSID> {
        GetSESSID(get())
    }
}