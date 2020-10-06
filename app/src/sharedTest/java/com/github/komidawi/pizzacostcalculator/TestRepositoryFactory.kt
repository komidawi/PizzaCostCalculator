package com.github.komidawi.pizzacostcalculator

import com.github.komidawi.pizzacostcalculator.data.LocalDataSource
import com.github.komidawi.pizzacostcalculator.data.PizzaRepository
import com.github.komidawi.pizzacostcalculator.data.PizzaRepositoryImpl
import com.github.komidawi.pizzacostcalculator.data.db.FakeDatabaseDao

object TestRepositoryFactory {

    fun create(): PizzaRepository {
        val databaseDao = FakeDatabaseDao()
        val localDataSource = LocalDataSource(databaseDao)
        return PizzaRepositoryImpl(localDataSource)
    }

}