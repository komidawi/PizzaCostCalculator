package com.github.komidawi.pizzacostcalculator

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabase
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabaseDao
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabaseDao_Impl
import kotlinx.coroutines.runBlocking


// TODO: replace with proper DI in the future
object ServiceLocator {

    private var database: PizzaDatabase? = null

    @Volatile
    var databaseDao: PizzaDatabaseDao? = null
        @VisibleForTesting set

    private val lock = Any()


    @VisibleForTesting
    fun resetDatabase() {
        synchronized(lock) {
            runBlocking { databaseDao?.deleteAll() }
            database?.apply {
                clearAllTables()
                close()
            }
            database = null
            databaseDao = null
        }
    }

    fun provideDatabaseDao(context: Context): PizzaDatabaseDao {
        synchronized(this) {
            return databaseDao ?: createDatabaseDao(context)
        }
    }

    private fun createDatabaseDao(context: Context): PizzaDatabaseDao {
        val db = database ?: createDatabase(context)
        val newDatabaseDao = PizzaDatabaseDao_Impl(db)
        databaseDao = newDatabaseDao
        return newDatabaseDao
    }

    private fun createDatabase(context: Context): PizzaDatabase {
        val result = Room.databaseBuilder(
            context.applicationContext,
            PizzaDatabase::class.java,
            "pizza_database"
        )
            .fallbackToDestructiveMigration() // TODO: add safe migrations
            .build()
        database = result
        return result
    }
}