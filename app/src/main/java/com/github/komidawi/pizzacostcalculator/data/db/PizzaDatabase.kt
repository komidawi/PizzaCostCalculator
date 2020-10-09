package com.github.komidawi.pizzacostcalculator.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PizzaEntity::class], version = 3, exportSchema = true)
abstract class PizzaDatabase : RoomDatabase() {

    abstract val pizzaDatabaseDao: PizzaDatabaseDao

}