package com.github.komidawi.pizzacostcalculator.database

import com.github.komidawi.pizzacostcalculator.model.PizzaModel

interface Repository {
    fun add(pizza: PizzaModel)
}