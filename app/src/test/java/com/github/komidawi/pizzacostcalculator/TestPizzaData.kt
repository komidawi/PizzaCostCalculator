package com.github.komidawi.pizzacostcalculator

import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntityFactory

object TestPizzaData {
    const val testName = "TestPizzaName"
    const val testSize = "42"
    const val testPrice = "24.99"

    const val testRatio = 180.3756022f

    /**
     * Precision delta for calculations
     */
    const val delta = 0.01f

    fun createTestPizza(): PizzaEntity = PizzaEntityFactory.create(testName, testSize, testPrice)
}