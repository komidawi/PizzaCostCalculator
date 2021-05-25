package com.github.komidawi.pizzacostcalculator.calc

import com.github.komidawi.pizzacostcalculator.domain.Pizza
import java.math.BigDecimal

interface CostCalculator {

    fun calculateRatioPerSqMeter(pizza: Pizza): BigDecimal

    fun calculateRatioPerSqMeter(size: BigDecimal, price: BigDecimal): BigDecimal

}