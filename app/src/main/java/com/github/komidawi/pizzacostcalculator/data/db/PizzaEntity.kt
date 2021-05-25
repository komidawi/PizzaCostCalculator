package com.github.komidawi.pizzacostcalculator.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.komidawi.pizzacostcalculator.domain.Pizza
import java.math.BigDecimal
import java.util.*

/**
 * Database-side Pizza object
 */
@Entity(tableName = "pizza")
data class PizzaEntity(

    val pizzeria: String?,

    val name: String,

    val size: String,

    val price: String,

    val ratio: String,

    @ColumnInfo(name = "delivery_cost", defaultValue = "0")
    val deliveryCost: String,

    val uuid: String,

    @PrimaryKey(autoGenerate = true)
    val id: Long? = null
)

fun PizzaEntity.asDomainModel(): Pizza =
    Pizza(
        pizzeria = pizzeria,
        name = name,
        size = BigDecimal(size),
        price = BigDecimal(price),
        ratio = BigDecimal(ratio),
        deliveryCost = BigDecimal(deliveryCost),
        uuid = UUID.fromString(uuid),
        id = id
    )

fun List<PizzaEntity>.asDomainModel(): List<Pizza> =
    map { pizzaEntity -> pizzaEntity.asDomainModel() }