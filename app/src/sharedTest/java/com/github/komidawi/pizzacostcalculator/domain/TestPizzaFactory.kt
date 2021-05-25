package com.github.komidawi.pizzacostcalculator.domain

import java.math.BigDecimal

object TestPizzaFactory {

    /**
     * Creates [Pizza] with empty non-numeric fields for testing purposes
     */
    fun create(size: Int, price: String): Pizza {
        val sizeNumber = BigDecimal(size)
        val priceNumber = BigDecimal(price)

        return PizzaFactory.create("", "", sizeNumber, priceNumber, BigDecimal.ZERO)
    }

}