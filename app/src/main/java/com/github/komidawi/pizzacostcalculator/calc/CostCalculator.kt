package com.github.komidawi.pizzacostcalculator.calc

import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import java.math.BigDecimal

object CostCalculator {

    private val sqCentimetersInSqMeter = BigDecimal(10000)

    fun calculateRatioPerSqMeter(pizza: PizzaEntity): BigDecimal {
        val d = pizza.size
        val r = d / BigDecimal(2)
        val areaInSqCentimeters = BigDecimal(Math.PI) * r.pow(2)
        val areaInSqMeters = areaInSqCentimeters / sqCentimetersInSqMeter

        return pizza.price / areaInSqMeters
    }
}