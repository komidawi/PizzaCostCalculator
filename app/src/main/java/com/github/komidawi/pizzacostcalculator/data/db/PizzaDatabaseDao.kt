package com.github.komidawi.pizzacostcalculator.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface PizzaDatabaseDao {

    @Insert
    suspend fun insert(pizzaEntity: PizzaEntity)

    @Query("SELECT * FROM pizza_table WHERE id = :id")
    suspend fun getById(id: Long): PizzaEntity?

    @Query("SELECT * FROM pizza_table WHERE uuid = :uuid")
    suspend fun getByUuid(uuid: String): PizzaEntity?

    @Query("SELECT * FROM pizza_table ORDER BY id DESC")
    fun getAll(): LiveData<List<PizzaEntity>>

    @Update
    suspend fun update(pizzaEntity: PizzaEntity)

    @Query("DELETE FROM pizza_table WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM pizza_table WHERE uuid = :uuid")
    suspend fun deleteByUuid(uuid: String)

    @Query("DELETE FROM pizza_table")
    suspend fun deleteAll()
}