package com.github.komidawi.pizzacostcalculator.calc

import com.github.komidawi.pizzacostcalculator.TestPizzaData.delta
import com.github.komidawi.pizzacostcalculator.domain.PizzaFactory
import org.junit.Assert.assertEquals
import org.junit.Test

class CostCalculatorTest {

    private val costCalculator: CostCalculator = CostCalculatorImpl()

    @Test
    fun calculateRatio_size42_cost24_99() {
        // given
        val pizza = PizzaFactory.create(42, "24.99")

        // when
        val ratio = costCalculator.calculateRatioPerSqMeter(pizza)

        // then
        assertEquals(180.37560, ratio.toDouble(), delta)
    }
}