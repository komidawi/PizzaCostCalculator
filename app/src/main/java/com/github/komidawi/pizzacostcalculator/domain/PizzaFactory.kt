package com.github.komidawi.pizzacostcalculator.domain

import com.github.komidawi.pizzacostcalculator.calc.CostCalculator
import java.math.BigDecimal

object PizzaFactory {

    private val calculator = CostCalculator

    /**
     * Makes providing numbers easier
     */
    fun create(pizzeria: String, name: String, size: Int, price: String): Pizza {
        val sizeNumber = BigDecimal(size)
        val priceNumber = BigDecimal(price)

        return create(pizzeria, name, sizeNumber, priceNumber)
    }

    /**
     * Makes providing numbers easier
     */
    fun create(pizzeria: String, name: String, size: String, price: String): Pizza {
        val sizeNumber = BigDecimal(size)
        val priceNumber = BigDecimal(price)

        return create(pizzeria, name, sizeNumber, priceNumber)
    }

    // TODO: move out of production code
    /**
     * Creates [Pizza] with empty non-numeric fields for testing purposes
     */
    fun create(size: Int, price: String): Pizza {
        val sizeNumber = BigDecimal(size)
        val priceNumber = BigDecimal(price)

        return create("", "", sizeNumber, priceNumber)
    }

    fun create(pizzeria: String, name: String, size: BigDecimal, price: BigDecimal): Pizza {
        val ratio = calculator.calculateRatioPerSqMeter(size, price)
        return Pizza(pizzeria, name, size, price, ratio)
    }
}