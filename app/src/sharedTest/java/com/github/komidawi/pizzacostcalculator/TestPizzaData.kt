package com.github.komidawi.pizzacostcalculator

import com.github.komidawi.pizzacostcalculator.domain.Pizza
import com.github.komidawi.pizzacostcalculator.domain.PizzaFactory

object TestPizzaData {

    const val testPizzeria = "tPizzeria"
    const val testName = "tPizza"
    const val testSize = "42"
    const val testPrice = "24.99"
    const val testDeliveryCost = "3.50"

    const val testRatio = 180.3756022

    const val testRatioDisplayText = "180"

    /**
     * Precision delta for calculations
     */
    const val delta = 0.01

    fun createTestPizza(): Pizza =
        PizzaFactory.create(testPizzeria, testName, testSize, testPrice, testDeliveryCost)
}