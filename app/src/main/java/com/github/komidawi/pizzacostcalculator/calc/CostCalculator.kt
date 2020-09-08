package com.github.komidawi.pizzacostcalculator.calc

import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import java.math.BigDecimal
import java.math.RoundingMode

object CostCalculator {

    private val sqCentimetersInSqMeter = BigDecimal(10000)

    fun calculateRatioPerSqMeter(pizza: PizzaEntity): BigDecimal =
        calculateRatioPerSqMeter(pizza.size, pizza.price)

    fun calculateRatioPerSqMeter(size: BigDecimal, price: BigDecimal): BigDecimal {
        val r = size.divide(BigDecimal(2), 2, RoundingMode.HALF_UP)
        val areaInSqCentimeters = BigDecimal.valueOf(Math.PI) * r.pow(2)
        val areaInSqMeters = areaInSqCentimeters / sqCentimetersInSqMeter

        return price / areaInSqMeters
    }
}