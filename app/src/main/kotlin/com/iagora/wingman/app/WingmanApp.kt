package com.iagora.wingman.app

import android.app.Application
import com.iagora.wingman.app.auth.di.authModule
import com.iagora.wingman.app.di.*
import com.iagora.wingman.app.main_menu.di.mainModule
import com.iagora.wingman.core.di.coreModule
import com.iagora.wingman.market.di.marketModule
import com.iagora.wingman.receive_order.di.receiveOrderModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.Forest.plant


class WingmanApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@WingmanApp)

            modules(coreModule)
            modules(appModule)
            modules(authModule)
            modules(mainModule)
            modules(marketModule)
            modules(receiveOrderModule)
        }
    }
}