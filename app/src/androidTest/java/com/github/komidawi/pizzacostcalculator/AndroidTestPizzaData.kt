package com.github.komidawi.pizzacostcalculator

import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity

object AndroidTestPizzaData {
    const val testName = "TestPizzaName"
    const val testSize = "42"
    const val testPrice = "24.99"

    fun createTestPizza(): PizzaEntity = PizzaEntity(testName, testSize, testPrice)
}