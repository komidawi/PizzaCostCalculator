package com.github.komidawi.pizzacostcalculator.model

import java.io.Serializable

data class PizzaModel(
    val name: String,
    val size: Float,
    val price: Float
) : Serializable