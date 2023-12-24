package com.foxdev.dailypulse.di

import app.cash.sqldelight.db.SqlDriver
import com.foxdev.dailypulse.db.DatabaseDriverFactory
import foxdev.dailypulse.db.DailyPulseDatabase
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory().createDriver() }
    single<DailyPulseDatabase> { DailyPulseDatabase(get()) }
}