package com.github.komidawi.pizzacostcalculator.domain

import com.github.komidawi.pizzacostcalculator.calc.CostCalculator
import java.math.BigDecimal

object PizzaFactory {

    private val calculator = CostCalculator

    /**
     * Makes providing numbers easier
     */
    fun create(name: String, size: Int, price: String): Pizza {
        val sizeNumber = BigDecimal(size)
        val priceNumber = BigDecimal(price)

        return create(name, sizeNumber, priceNumber)
    }

    /**
     * Makes providing numbers easier
     */
    fun create(name: String, size: String, price: String): Pizza {
        val sizeNumber = BigDecimal(size)
        val priceNumber = BigDecimal(price)

        return create(name, sizeNumber, priceNumber)
    }

    /**
     * Creates [Pizza] with empty name for testing purposes
     */
    fun create(size: Int, price: String): Pizza {
        val sizeNumber = BigDecimal(size)
        val priceNumber = BigDecimal(price)

        return create("", sizeNumber, priceNumber)
    }

    fun create(name: String, size: BigDecimal, price: BigDecimal): Pizza {
        val ratio = calculator.calculateRatioPerSqMeter(size, price)
        return Pizza(null, name, size, price, ratio)
    }
}