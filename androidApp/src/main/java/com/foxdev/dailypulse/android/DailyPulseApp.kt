package com.foxdev.dailypulse.android

import android.app.Application
import com.foxdev.dailypulse.android.di.databaseModule
import com.foxdev.dailypulse.android.di.viewModelsModule
import com.foxdev.dailypulse.di.sharedModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DailyPulseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val modules = sharedModules + viewModelsModule + databaseModule

        startKoin {
            androidContext(this@DailyPulseApp)
            modules(modules)
        }
    }
}