package com.github.komidawi.pizzacostcalculator.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.*

@Entity(tableName = "pizza_table")
data class PizzaEntity(

    val name: String,

    val size: BigDecimal,

    val price: BigDecimal,

    val uuid: String = UUID.randomUUID().toString(),

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
)