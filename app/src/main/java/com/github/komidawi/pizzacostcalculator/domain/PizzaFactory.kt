package com.github.komidawi.pizzacostcalculator.domain

import com.github.komidawi.pizzacostcalculator.calc.CostCalculator
import java.math.BigDecimal

object PizzaFactory {

    private val calculator = CostCalculator

    /**
     * Makes providing numbers easier
     */
    fun create(
        pizzeria: String,
        name: String,
        size: Int,
        price: String,
        deliveryCost: String
    ): Pizza {
        val sizeNumber = BigDecimal(size)
        val priceNumber = BigDecimal(price)
        val deliveryCostNumber = BigDecimal(deliveryCost)

        return create(pizzeria, name, sizeNumber, priceNumber, deliveryCostNumber)
    }

    /**
     * Makes providing numbers easier
     */
    fun create(
        pizzeria: String,
        name: String,
        size: String,
        price: String,
        deliveryCost: String
    ): Pizza {
        val sizeNumber = BigDecimal(size)
        val priceNumber = BigDecimal(price)
        val deliveryCostNumber = BigDecimal(deliveryCost)

        return create(pizzeria, name, sizeNumber, priceNumber, deliveryCostNumber)
    }

    // TODO: move out of production code
    /**
     * Creates [Pizza] with empty non-numeric fields for testing purposes
     */
    fun create(size: Int, price: String): Pizza {
        val sizeNumber = BigDecimal(size)
        val priceNumber = BigDecimal(price)

        return create("", "", sizeNumber, priceNumber, BigDecimal.ZERO)
    }

    fun create(
        pizzeria: String,
        name: String,
        size: BigDecimal,
        price: BigDecimal,
        deliveryCost: BigDecimal
    ): Pizza {
        val ratio = calculator.calculateRatioPerSqMeter(size, price)
        return Pizza(pizzeria, name, size, price, ratio, deliveryCost)
    }
}