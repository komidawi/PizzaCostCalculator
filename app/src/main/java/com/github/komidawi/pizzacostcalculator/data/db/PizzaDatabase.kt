package com.github.komidawi.pizzacostcalculator.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

// TODO: set exportSchema to true
@Database(entities = [PizzaEntity::class], version = 3, exportSchema = false)
abstract class PizzaDatabase : RoomDatabase() {

    abstract val pizzaDatabaseDao: PizzaDatabaseDao

}