package com.github.komidawi.pizzacostcalculator.data.datasource

import androidx.lifecycle.LiveData
import com.github.komidawi.pizzacostcalculator.domain.Pizza
import com.github.komidawi.pizzacostcalculator.domain.asNetworkModel
import com.github.komidawi.pizzacostcalculator.network.RestApiService
import com.github.komidawi.pizzacostcalculator.network.asDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class RemoteDataSource(
    private val restService: RestApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : DataSource {


    override suspend fun insert(pizza: Pizza) {
        withContext(ioDispatcher) {
            restService.insertPizza(pizza.asNetworkModel())
        }
    }

    override suspend fun insertAll(pizzas: List<Pizza>) {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Long): Pizza? {
        TODO("Not yet implemented")
    }

    override suspend fun getByUuid(uuid: UUID): Pizza? {
        val foundPizza = withContext(ioDispatcher) {
            return@withContext restService.getPizzaByUuid(uuid.toString())
        }
        return foundPizza?.asDomainModel()
    }

    override fun getAll(): LiveData<List<Pizza>> {
        TODO("Not yet implemented")
    }

    override suspend fun update(pizza: Pizza) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteById(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteByUuid(uuid: UUID) {
        withContext(ioDispatcher) {
            restService.deletePizzaByUuid(uuid.toString())
        }
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

}