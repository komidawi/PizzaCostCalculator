package com.github.komidawi.pizzacostcalculator.model

import java.math.BigDecimal

data class PizzaModel(
    val name: String,
    val size: BigDecimal,
    val price: BigDecimal
)