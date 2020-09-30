package com.github.komidawi.pizzacostcalculator.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.komidawi.pizzacostcalculator.domain.Pizza
import java.math.BigDecimal
import java.util.*

// TODO: probably use Strings instead of BigDecimals
/**
 * Database-side Pizza object
 */
@Entity(tableName = "pizza_table")
data class PizzaEntity(

    val name: String,

    val size: BigDecimal,

    val price: BigDecimal,

    val ratio: BigDecimal,

    val uuid: String = UUID.randomUUID().toString(),

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
)

fun PizzaEntity.asDomainModel(): Pizza =
    Pizza(id, name, size, price, ratio, UUID.fromString(uuid))