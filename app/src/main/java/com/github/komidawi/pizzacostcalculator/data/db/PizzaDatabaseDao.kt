package com.github.komidawi.pizzacostcalculator.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PizzaDatabaseDao {

    @Insert
    suspend fun insert(pizzaEntity: PizzaEntity)

    @Insert
    suspend fun insertAll(pizzaEntities: List<PizzaEntity>)

    @Query("SELECT * FROM pizza WHERE id = :id")
    suspend fun getById(id: Long): PizzaEntity?

    @Query("SELECT * FROM pizza WHERE uuid = :uuid")
    suspend fun getByUuid(uuid: String): PizzaEntity?

    @Query("SELECT * FROM pizza ORDER BY id DESC")
    fun getAll(): LiveData<List<PizzaEntity>>

    @Update
    suspend fun update(pizzaEntity: PizzaEntity)

    @Query("DELETE FROM pizza WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM pizza WHERE uuid = :uuid")
    suspend fun deleteByUuid(uuid: String)

    @Query("DELETE FROM pizza")
    suspend fun deleteAll()
}