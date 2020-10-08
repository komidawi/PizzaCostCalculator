package com.github.komidawi.pizzacostcalculator.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PizzaDatabaseDao {

    // TODO: trzeba zrobić tak, żeby insert w razie jak nie ma ID nadawał je

    @Insert
    suspend fun insert(pizzaEntity: PizzaEntity)

    @Insert
    suspend fun insertAll(pizzaEntities: List<PizzaEntity>)

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