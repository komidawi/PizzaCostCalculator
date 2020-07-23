package com.github.komidawi.pizzacostcalculator.data.db

import com.github.komidawi.pizzacostcalculator.data.model.PizzaModel

interface Repository {
    fun add(pizza: PizzaModel)
}