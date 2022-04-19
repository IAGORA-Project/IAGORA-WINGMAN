package com.iagora.wingman.core.di.component

import com.iagora.wingman.core.data.usecase.GetPref
import com.iagora.wingman.core.data.usecase.GetSESSID
import com.iagora.wingman.core.data.usecase.SavePref
import com.iagora.wingman.core.domain.usecase.IGetPref
import com.iagora.wingman.core.domain.usecase.IGetSESSID
import com.iagora.wingman.core.domain.usecase.ISavePref
import org.koin.dsl.module

val useCaseModule = module {
    single<IGetSESSID> {
        GetSESSID(get())
    }
    single<ISavePref> { SavePref(get()) }
    single<IGetPref> { GetPref(get()) }
}