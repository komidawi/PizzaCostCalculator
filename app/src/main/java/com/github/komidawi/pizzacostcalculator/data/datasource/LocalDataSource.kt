package com.github.komidawi.pizzacostcalculator.data.datasource

import androidx.lifecycle.LiveData
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabaseDao
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity

class LocalDataSource(private val pizzaDatabaseDao: PizzaDatabaseDao) : DataSource {

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