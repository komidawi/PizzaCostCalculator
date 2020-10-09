package com.github.komidawi.pizzacostcalculator.data.repository

import androidx.lifecycle.LiveData
import com.github.komidawi.pizzacostcalculator.data.datasource.DataSource
import com.github.komidawi.pizzacostcalculator.domain.Pizza
import com.github.komidawi.pizzacostcalculator.network.RestApi
import com.github.komidawi.pizzacostcalculator.network.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class PizzaRepositoryImpl(private val localDataSource: DataSource) : PizzaRepository {

    suspend fun refreshPizzas() {
        withContext(Dispatchers.IO) {
            val allPizzas = RestApi.retrofitService.getAllPizzas()
            localDataSource.insertAll(allPizzas.asDomainModel())
        }
    }

    override suspend fun insert(pizza: Pizza) {
        localDataSource.insert(pizza)
    }

    override suspend fun insertAll(pizzas: List<Pizza>) {
        localDataSource.insertAll(pizzas)
    }

    override suspend fun getById(id: Long): Pizza? = localDataSource.getById(id)

    override suspend fun getByUuid(uuid: UUID): Pizza? =
        localDataSource.getByUuid(uuid)

    override fun getAll(): LiveData<List<Pizza>> = localDataSource.getAll()

    override suspend fun update(pizza: Pizza) {
        localDataSource.update(pizza)
    }

    override suspend fun deleteById(id: Long) {
        localDataSource.deleteById(id)
    }

    override suspend fun deleteByUuid(uuid: UUID) {
        localDataSource.deleteByUuid(uuid)
    }

    override suspend fun deleteAll() {
        localDataSource.deleteAll()
    }
}