package com.github.komidawi.pizzacostcalculator.network

import com.github.komidawi.pizzacostcalculator.domain.Pizza
import java.math.BigDecimal
import java.util.*

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

fun PizzaDto.asDomainModel(): Pizza =
    Pizza(name, BigDecimal(size), BigDecimal(price), BigDecimal(ratio), UUID.fromString(uuid), id)

fun List<PizzaDto>.asDomainModel(): List<Pizza> = map { it.asDomainModel() }