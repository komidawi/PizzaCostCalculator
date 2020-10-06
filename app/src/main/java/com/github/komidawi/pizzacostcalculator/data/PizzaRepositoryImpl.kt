package com.github.komidawi.pizzacostcalculator.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import com.github.komidawi.pizzacostcalculator.data.db.asDomainModel
import com.github.komidawi.pizzacostcalculator.domain.Pizza
import com.github.komidawi.pizzacostcalculator.network.RestApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PizzaRepositoryImpl(private val localDataSource: DataSource) : PizzaRepository {

    val pizzas: LiveData<List<Pizza>> = Transformations.map(localDataSource.getAll()) {
        it.asDomainModel()
    }

    suspend fun refreshPizzas() {
        withContext(Dispatchers.IO) {
            val allPizzas = RestApi.retrofitService.getAllPizzas()
            localDataSource.insertAll(allPizzas)
        }
    }

    override suspend fun insert(pizzaEntity: PizzaEntity) {
        localDataSource.insert(pizzaEntity)
    }

    override suspend fun insertAll(pizzaEntities: List<PizzaEntity>) {
        localDataSource.insertAll(pizzaEntities)
    }

    override suspend fun getById(id: Long): PizzaEntity? = localDataSource.getById(id)

    override suspend fun getByUuid(uuid: String): PizzaEntity? = localDataSource.getByUuid(uuid)

    override fun getAll(): LiveData<List<PizzaEntity>> = localDataSource.getAll()

    override suspend fun update(pizzaEntity: PizzaEntity) {
        localDataSource.update(pizzaEntity)
    }

    override suspend fun deleteById(id: Long) {
        localDataSource.deleteById(id)
    }

    override suspend fun deleteByUuid(uuid: String) {
        localDataSource.deleteByUuid(uuid)
    }

    override suspend fun deleteAll() {
        localDataSource.deleteAll()
    }
}