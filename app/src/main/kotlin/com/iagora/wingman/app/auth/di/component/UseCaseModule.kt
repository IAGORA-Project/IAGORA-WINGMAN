package com.iagora.wingman.app.auth.di.component

import com.iagora.wingman.app.auth.data.usecase.RequestLogin
import com.iagora.wingman.app.auth.data.usecase.RequestOTP
import com.iagora.wingman.app.auth.domain.usecase.IRequestLogin
import com.iagora.wingman.app.auth.domain.usecase.IRequestOTP
import org.koin.dsl.module

val useCaseModule = module {
    single<IRequestLogin> {
        RequestLogin(get())
    }
    single<IRequestOTP> {
        RequestOTP(get())
    }
}