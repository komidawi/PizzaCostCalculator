package com.github.komidawi.pizzacostcalculator.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PizzaDatabaseDao {

    @Insert
    fun insert(pizzaEntity: PizzaEntity)

    @Query("SELECT * FROM pizza_table WHERE id = :id")
    fun getById(id: Long): PizzaEntity?

    @Query("SELECT * FROM pizza_table ORDER BY id DESC")
    fun getAll(): LiveData<List<PizzaEntity>>

    @Update
    fun update(pizzaEntity: PizzaEntity)

    @Query("DELETE FROM pizza_table WHERE id = :id")
    fun delete(id: Long)

    @Query("DELETE FROM pizza_table")
    fun deleteAll()
}