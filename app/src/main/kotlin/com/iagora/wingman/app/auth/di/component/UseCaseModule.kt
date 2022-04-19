package com.iagora.wingman.app.auth.di.component

import com.iagora.wingman.app.auth.data.usecase.*
import com.iagora.wingman.app.auth.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    single<IRequestOTP> {
        RequestOTP(get())
    }

    single<IVerifyOTP>{
        VerifyOTP(get())
    }

    single<IAccessToken>{
        RequestAccessToken(get())
    }
    single<IRegisterComplate>{
        RegisterComplate(get())
    }
    single<IRegisterDetail>{
        RegisterDetail(get())
    }

    single<ISetPersonalInfo> { SetPersonalInfo() }
}