package com.github.komidawi.pizzacostcalculator.database

import com.github.komidawi.pizzacostcalculator.model.PizzaModel

object SampleRepository : Repository {
    val pizzaList = mutableListOf<PizzaModel>()

    override fun add(pizza: PizzaModel) {
        pizzaList.add(pizza)
    }
}