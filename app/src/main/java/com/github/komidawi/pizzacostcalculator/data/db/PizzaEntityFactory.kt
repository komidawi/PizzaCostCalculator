package com.github.komidawi.pizzacostcalculator.data.db

import com.github.komidawi.pizzacostcalculator.calc.CostCalculator
import java.math.BigDecimal

object PizzaEntityFactory {

    private val calculator = CostCalculator

    /**
     * Makes providing numbers easier
     */
    fun create(name: String, size: Int, price: String): PizzaEntity {
        val sizeNumber = BigDecimal(size)
        val priceNumber = BigDecimal(price)

        return create(name, sizeNumber, priceNumber)
    }

    /**
     * Makes providing numbers easier
     */
    fun create(name: String, size: String, price: String): PizzaEntity {
        val sizeNumber = BigDecimal(size)
        val priceNumber = BigDecimal(price)

        return create(name, sizeNumber, priceNumber)
    }

    /**
     * Creates [PizzaEntity] with empty name for testing purposes
     */
    fun create(size: Int, price: String): PizzaEntity {
        val sizeNumber = BigDecimal(size)
        val priceNumber = BigDecimal(price)

        return create("", sizeNumber, priceNumber)
    }

    fun create(name: String, size: BigDecimal, price: BigDecimal): PizzaEntity {
        val ratio = calculator.calculateRatioPerSqMeter(size, price)
        return PizzaEntity(name, size, price, ratio)
    }
}