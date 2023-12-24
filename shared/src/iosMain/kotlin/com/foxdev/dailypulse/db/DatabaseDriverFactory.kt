package com.foxdev.dailypulse.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import foxdev.dailypulse.db.DailyPulseDatabase

actual class DatabaseDriverFactory {

    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = DailyPulseDatabase.Schema,
            name = "DailyPulse.Database.db"
        )
    }
}