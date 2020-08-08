package com.github.komidawi.pizzacostcalculator.screen.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabaseDao
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity

class DummyDatabaseDao : PizzaDatabaseDao {

    override fun insert(pizzaEntity: PizzaEntity) {}

    override fun getById(id: Long): PizzaEntity? = null

    override fun getAll(): LiveData<List<PizzaEntity>> = MutableLiveData<List<PizzaEntity>>()

    override fun update(pizzaEntity: PizzaEntity) {}

    override fun delete(id: Long) {}

    override fun deleteAll() {}
}