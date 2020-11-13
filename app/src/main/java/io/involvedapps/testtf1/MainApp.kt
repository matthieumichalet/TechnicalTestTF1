package io.involvedapps.testtf1

import android.app.Application
import io.involvedapps.testtf1.conf.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(listOf(
                viewModels,
                useCaseModules,
                repositories,
                networkModules,
                localModules
            ))
        }
    }

}