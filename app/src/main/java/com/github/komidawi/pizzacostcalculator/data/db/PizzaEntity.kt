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
) {
    /**
     * Overloaded constructor to make providing numbers easier
     */
    constructor(name: String, size: Int, price: String) :
            this(name, BigDecimal(size), BigDecimal(price))

    /**
     * Overloaded constructor to make providing numbers easier
     */
    constructor(name: String, size: String, price: String) :
            this(name, BigDecimal(size), BigDecimal(price))

    /**
     * Overloaded constructor with empty name for testing purposes
     */
    constructor(size: Int, price: String) :
            this("", BigDecimal(size), BigDecimal(price))
}