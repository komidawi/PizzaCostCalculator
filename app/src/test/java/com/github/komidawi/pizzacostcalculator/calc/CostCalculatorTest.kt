package com.github.komidawi.pizzacostcalculator.calc

import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import org.junit.Assert.*
import org.junit.Test

class CostCalculatorTest {

    companion object {
        private const val DELTA = 0.01f
    }

    @Test
    fun calculateRatio_size42_cost24_99() {
        // given
        val pizza = PizzaEntity(42, "24.99")

        // when
        val ratio = CostCalculator.calculateRatioPerSqMeter(pizza)

        // then
        assertEquals(180.37560f, ratio.toFloat(), DELTA)
    }
}