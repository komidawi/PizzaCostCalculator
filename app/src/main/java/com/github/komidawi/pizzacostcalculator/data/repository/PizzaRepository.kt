package com.github.komidawi.pizzacostcalculator.data.repository

import androidx.lifecycle.LiveData
import com.github.komidawi.pizzacostcalculator.domain.Pizza
import java.util.*

interface PizzaRepository {

    suspend fun insert(pizza: Pizza)

    suspend fun insertAll(pizzas: List<Pizza>)

    suspend fun getById(id: Long): Pizza?

    suspend fun getByUuid(uuid: UUID): Pizza?

    fun getAll(): LiveData<List<Pizza>>

    suspend fun update(pizza: Pizza)

    suspend fun deleteById(id: Long)

    suspend fun deleteByUuid(uuid: UUID)

    suspend fun deleteAll()

}