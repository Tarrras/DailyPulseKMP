package com.foxdev.dailypulse.android.di

import app.cash.sqldelight.db.SqlDriver
import com.foxdev.dailypulse.db.DatabaseDriverFactory
import foxdev.dailypulse.db.DailyPulseDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory(androidContext()).createDriver() }
    single<DailyPulseDatabase> { DailyPulseDatabase(get()) }
}