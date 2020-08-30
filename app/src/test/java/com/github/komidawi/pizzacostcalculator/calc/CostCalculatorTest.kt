package com.github.komidawi.pizzacostcalculator.calc

import com.github.komidawi.pizzacostcalculator.TestPizzaData.delta
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntityFactory
import org.junit.Assert.*
import org.junit.Test

class CostCalculatorTest {

    @Test
    fun calculateRatio_size42_cost24_99() {
        // given
        val pizza = PizzaEntityFactory.create(42, "24.99")

        // when
        val ratio = CostCalculator.calculateRatioPerSqMeter(pizza)

        // then
        assertEquals(180.37560f, ratio.toFloat(), delta)
    }
}