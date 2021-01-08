package com.github.komidawi.pizzacostcalculator.network

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