package com.github.komidawi.pizzacostcalculator

import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntityFactory

object AndroidTestPizzaData {
    const val testName = "TestPizzaName"
    const val testSize = "42"
    const val testPrice = "24.99"

    const val testRatioDisplayText = "180"

    fun createTestPizza(): PizzaEntity = PizzaEntityFactory.create(testName, testSize, testPrice)
}