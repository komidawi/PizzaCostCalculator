package com.github.komidawi.pizzacostcalculator.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabaseDao
import com.github.komidawi.pizzacostcalculator.data.db.asDomainModel
import com.github.komidawi.pizzacostcalculator.domain.Pizza
import com.github.komidawi.pizzacostcalculator.domain.asDatabaseModel
import java.util.*

class LocalDataSource(private val pizzaDatabaseDao: PizzaDatabaseDao) : DataSource {

    override suspend fun insert(pizza: Pizza) {
        pizzaDatabaseDao.insert(pizza.asDatabaseModel())
    }

    override suspend fun insertAll(pizzas: List<Pizza>) {
        pizzaDatabaseDao.insertAll(pizzas.asDatabaseModel())
    }

    override suspend fun getById(id: Long): Pizza? = pizzaDatabaseDao.getById(id)?.asDomainModel()

    override suspend fun getByUuid(uuid: UUID): Pizza? =
        pizzaDatabaseDao.getByUuid(uuid.toString())?.asDomainModel()

    override fun getAll(): LiveData<List<Pizza>> =
        Transformations.map(pizzaDatabaseDao.getAll()) {
            it.asDomainModel()
        }

    override suspend fun update(pizza: Pizza) {
        pizzaDatabaseDao.update(pizza.asDatabaseModel())
    }

    override suspend fun deleteById(id: Long) {
        pizzaDatabaseDao.deleteById(id)
    }

    override suspend fun deleteByUuid(uuid: UUID) {
        pizzaDatabaseDao.deleteByUuid(uuid.toString())
    }

    override suspend fun deleteAll() {
        pizzaDatabaseDao.deleteAll()
    }

}