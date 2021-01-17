package com.github.komidawi.pizzacostcalculator

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.github.komidawi.pizzacostcalculator.data.datasource.LocalDataSource
import com.github.komidawi.pizzacostcalculator.data.db.Migrations
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabase
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabaseDao
import com.github.komidawi.pizzacostcalculator.data.repository.PizzaRepository
import com.github.komidawi.pizzacostcalculator.data.repository.PizzaRepositoryImpl
import kotlinx.coroutines.runBlocking


object ServiceLocator {

    private var database: PizzaDatabase? = null

    private var databaseDao: PizzaDatabaseDao? = null

    @Volatile
    var repository: PizzaRepository? = null
        @VisibleForTesting set

    private val lock = Any()


    @VisibleForTesting
    fun resetRepository() {
        synchronized(lock) {
            runBlocking {
                databaseDao?.deleteAll()
            }
            database?.apply {
                clearAllTables()
                close()
            }
            database = null
            databaseDao = null
            repository = null
        }
    }

    fun providePizzaRepository(context: Context): PizzaRepository {
        synchronized(this) {
            return repository ?: createPizzaRepository(context)
        }
    }

    private fun createPizzaRepository(context: Context): PizzaRepository {
        val pizzaDatabaseDao = createDatabaseDao(context)
        val localDataSource = LocalDataSource(pizzaDatabaseDao)
        val newRepository = PizzaRepositoryImpl(localDataSource)
        repository = newRepository
        return newRepository
    }

    private fun createDatabaseDao(context: Context): PizzaDatabaseDao {
        val db = database ?: createDatabase(context)
        val newDatabaseDao = db.pizzaDatabaseDao
        databaseDao = newDatabaseDao
        return newDatabaseDao
    }

    private fun createDatabase(context: Context): PizzaDatabase {
        val result = Room.databaseBuilder(
            context.applicationContext,
            PizzaDatabase::class.java,
            "pizza_database"
        )
            .addMigrations(Migrations.MIGRATION_3_4)
            .build()
        database = result
        return result
    }
}