package com.github.komidawi.pizzacostcalculator.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.komidawi.pizzacostcalculator.domain.Pizza
import java.math.BigDecimal
import java.util.*

/**
 * Database-side Pizza object
 */
@Entity(tableName = "pizza_table")
data class PizzaEntity(

    val name: String,

    val size: String,

    val price: String,

    val ratio: String,

    val uuid: String,

    @PrimaryKey(autoGenerate = true)
    val id: Long? = null
)

fun PizzaEntity.asDomainModel(): Pizza =
    Pizza(name, BigDecimal(size), BigDecimal(price), BigDecimal(ratio), UUID.fromString(uuid), id)

fun List<PizzaEntity>.asDomainModel(): List<Pizza> =
    map { pizzaEntity -> pizzaEntity.asDomainModel() }