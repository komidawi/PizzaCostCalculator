package com.github.komidawi.pizzacostcalculator.network

import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import java.math.BigDecimal

/**
 * Network-side Pizza object
 */
data class PizzaDto(

    val id: Long,

    val name: String,

    val size: String,

    val price: String,

    val ratio: String,

    val uuid: String

)

fun PizzaDto.asDatabaseModel(): PizzaEntity =
    PizzaEntity(name, BigDecimal(size), BigDecimal(price), BigDecimal(ratio), uuid, id)
