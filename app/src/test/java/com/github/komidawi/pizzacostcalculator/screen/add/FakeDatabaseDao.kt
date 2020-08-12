package com.github.komidawi.pizzacostcalculator.screen.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabaseDao
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity

class FakeDatabaseDao : PizzaDatabaseDao {

    private val data = hashMapOf<Long, PizzaEntity>()

    private val observableData = MutableLiveData<List<PizzaEntity>>()

    override fun insert(pizzaEntity: PizzaEntity) {
        data[pizzaEntity.id] = pizzaEntity
    }

    override fun getById(id: Long): PizzaEntity? = data[id]

    override fun getAll(): LiveData<List<PizzaEntity>> {
        observableData.value = data.values.toList()
        return observableData
    }

    override fun update(pizzaEntity: PizzaEntity) {
        data[pizzaEntity.id] = pizzaEntity
    }

    override fun delete(id: Long) {
        data.remove(id)
    }

    override fun deleteAll() {
        data.clear()
    }
}