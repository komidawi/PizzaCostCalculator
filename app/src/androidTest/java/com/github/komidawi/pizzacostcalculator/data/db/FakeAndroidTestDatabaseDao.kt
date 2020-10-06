package com.github.komidawi.pizzacostcalculator.data.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeAndroidTestDatabaseDao : PizzaDatabaseDao {

    private val data = hashSetOf<PizzaEntity>()

    private val observableData = MutableLiveData<List<PizzaEntity>>()


    override suspend fun insert(pizzaEntity: PizzaEntity) {
        data.add(pizzaEntity)
    }

    override suspend fun insertAll(pizzaEntities: List<PizzaEntity>) {
        data.addAll(pizzaEntities)
    }

    override suspend fun getById(id: Long): PizzaEntity? =
        data.stream().filter { it.id == id }.findAny().orElse(null)

    override suspend fun getByUuid(uuid: String): PizzaEntity? =
        data.stream().filter { it.uuid == uuid }.findAny().orElse(null)

    override fun getAll(): LiveData<List<PizzaEntity>> {
        observableData.postValue(data.toList())
        return observableData
    }

    override suspend fun update(pizzaEntity: PizzaEntity) {
        val current = data.stream().filter { it.uuid == pizzaEntity.uuid }.findAny()
        current.ifPresent { data.remove(it) }
        data.add(pizzaEntity)
    }

    override suspend fun deleteById(id: Long) {
        val current = data.stream().filter { it.id == id }.findAny()
        current.ifPresent { data.remove(it) }
    }

    override suspend fun deleteByUuid(uuid: String) {
        val current = data.stream().filter { it.uuid == uuid }.findAny()
        current.ifPresent { data.remove(it) }
    }

    override suspend fun deleteAll() {
        data.clear()
    }
}