package com.github.komidawi.pizzacostcalculator.domain

import com.github.komidawi.pizzacostcalculator.calc.CostCalculatorImpl
import java.math.BigDecimal

object PizzaFactory {

    private val calculator = CostCalculatorImpl()

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