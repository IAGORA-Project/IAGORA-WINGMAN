package com.iagora.wingman.app

import android.app.Application
import com.iagora.wingman.app.di.*
import com.iagora.wingman.core.di.networkModule
import com.iagora.wingman.market.features.main_features.di.marketModule
import com.iagora.wingman.process_order.features.main_features.di.processOrderModule
import com.iagora.wingman.receive_order.features.main_features.di.receiveOrderModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin


class WingmanApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WingmanApp)
            loadKoinModules(
                listOf(
                    networkModule,
                    apiModule,
                    repositoryModule,
                    ViewModelModule,
                    otherModule,

                )
            )
            processOrderModule
            receiveOrderModule
            marketModule
        }
    }
}