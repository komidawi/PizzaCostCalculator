package com.github.komidawi.pizzacostcalculator.calc

import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import java.math.BigDecimal

object CostCalculator {

    private val sqCentimetersInSqMeter = BigDecimal(10000)

    fun calculateRatioPerSqMeter(pizza: PizzaEntity): BigDecimal =
        calculateRatioPerSqMeter(pizza.size, pizza.price)

    fun calculateRatioPerSqMeter(size: BigDecimal, price: BigDecimal): BigDecimal {
        val r = size / BigDecimal(2)
        val areaInSqCentimeters = BigDecimal(Math.PI) * r.pow(2)
        val areaInSqMeters = areaInSqCentimeters / sqCentimetersInSqMeter

        return price / areaInSqMeters
    }
}