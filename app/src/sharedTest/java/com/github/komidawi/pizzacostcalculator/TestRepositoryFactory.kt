package com.github.komidawi.pizzacostcalculator

import com.github.komidawi.pizzacostcalculator.data.datasource.LocalDataSource
import com.github.komidawi.pizzacostcalculator.data.db.FakeDatabaseDao
import com.github.komidawi.pizzacostcalculator.data.repository.PizzaRepository
import com.github.komidawi.pizzacostcalculator.data.repository.PizzaRepositoryImpl

object TestRepositoryFactory {

    fun create(): PizzaRepository {
        val databaseDao = FakeDatabaseDao()
        val localDataSource = LocalDataSource(databaseDao)
        return PizzaRepositoryImpl(localDataSource)
    }

}