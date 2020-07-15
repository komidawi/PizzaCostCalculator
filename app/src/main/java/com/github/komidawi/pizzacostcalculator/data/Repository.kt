package com.github.komidawi.pizzacostcalculator.data

import com.github.komidawi.pizzacostcalculator.model.PizzaModel

interface Repository {
    fun add(pizza: PizzaModel)
}