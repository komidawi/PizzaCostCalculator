package com.github.komidawi.pizzacostcalculator.domain

import java.math.BigDecimal
import java.util.*

/**
 * Domain Pizza object
 */
class Pizza(

    val id: Long,

    val name: String,

    val size: BigDecimal,

    val price: BigDecimal,

    val ratio: BigDecimal,

    val uuid: UUID

)