package com.github.komidawi.pizzacostcalculator.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabaseDao
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import com.github.komidawi.pizzacostcalculator.data.db.asDomainModel
import com.github.komidawi.pizzacostcalculator.domain.Pizza
import com.github.komidawi.pizzacostcalculator.network.RestApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PizzaRepositoryImpl(private val pizzaDatabaseDao: PizzaDatabaseDao) : PizzaRepository {

    val pizzas: LiveData<List<Pizza>> = Transformations.map(pizzaDatabaseDao.getAll()) {
        it.asDomainModel()
    }

    suspend fun refreshPizzas() {
        withContext(Dispatchers.IO) {
            val allPizzas = RestApi.retrofitService.getAllPizzas()
            pizzaDatabaseDao.insertAll(allPizzas)
        }
    }

    override suspend fun insert(pizzaEntity: PizzaEntity) {
        pizzaDatabaseDao.insert(pizzaEntity)
    }

    override suspend fun insertAll(pizzaEntities: List<PizzaEntity>) {
        pizzaDatabaseDao.insertAll(pizzaEntities)
    }

    override suspend fun getById(id: Long): PizzaEntity? = pizzaDatabaseDao.getById(id)

    override suspend fun getByUuid(uuid: String): PizzaEntity? = pizzaDatabaseDao.getByUuid(uuid)

    override fun getAll(): LiveData<List<PizzaEntity>> = pizzaDatabaseDao.getAll()

    override suspend fun update(pizzaEntity: PizzaEntity) {
        pizzaDatabaseDao.update(pizzaEntity)
    }

    override suspend fun deleteById(id: Long) {
        pizzaDatabaseDao.deleteById(id)
    }

    override suspend fun deleteByUuid(uuid: String) {
        pizzaDatabaseDao.deleteByUuid(uuid)
    }

    override suspend fun deleteAll() {
        pizzaDatabaseDao.deleteAll()
    }
}