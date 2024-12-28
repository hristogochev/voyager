package io.github.hristogochev.vortex.sample

import android.app.Application
import io.github.hristogochev.vortex.sample.kodeinIntegration.KodeinScreenModel
import io.github.hristogochev.vortex.sample.koinIntegration.KoinScreenModel
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.bindProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

class App : Application(), DIAware {

    override val di by DI.lazy {
        androidXModule(this@App)
        bindProvider { KodeinScreenModel() }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                module {
                    factoryOf(::KoinScreenModel)
                }
            )
        }
    }
}
