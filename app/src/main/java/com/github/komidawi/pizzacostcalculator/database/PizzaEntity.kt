package com.github.komidawi.pizzacostcalculator.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pizza_table")
data class PizzaEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    val name: String,

    val size: Float,

    val price: Float
)