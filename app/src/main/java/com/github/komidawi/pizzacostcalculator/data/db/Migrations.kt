package com.github.komidawi.pizzacostcalculator.data.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migrations {

    val MIGRATION_3_4 = object : Migration(3, 4) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE pizza_table RENAME TO pizza")
            database.execSQL("ALTER TABLE pizza ADD pizzeria TEXT")
        }
    }

    val MIGRATION_4_5 = object : Migration(4, 5) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE pizza ADD delivery_cost TEXT DEFAULT '0' NOT NULL")
        }
    }

}