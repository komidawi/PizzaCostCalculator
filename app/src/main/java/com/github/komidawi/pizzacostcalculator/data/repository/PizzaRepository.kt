package com.github.komidawi.pizzacostcalculator.data.repository

import androidx.lifecycle.LiveData
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity

interface PizzaRepository {

    suspend fun insert(pizzaEntity: PizzaEntity)

    suspend fun insertAll(pizzaEntities: List<PizzaEntity>)

    suspend fun getById(id: Long): PizzaEntity?

    suspend fun getByUuid(uuid: String): PizzaEntity?

    fun getAll(): LiveData<List<PizzaEntity>>

    suspend fun update(pizzaEntity: PizzaEntity)

    suspend fun deleteById(id: Long)

    suspend fun deleteByUuid(uuid: String)

    suspend fun deleteAll()

}