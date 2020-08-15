package com.github.komidawi.pizzacostcalculator.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

// TODO: set exportSchema to true
@Database(entities = [PizzaEntity::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PizzaDatabase : RoomDatabase() {

    abstract val pizzaDatabaseDao: PizzaDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: PizzaDatabase? = null

        fun getInstance(context: Context): PizzaDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PizzaDatabase::class.java,
                        "pizza_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}