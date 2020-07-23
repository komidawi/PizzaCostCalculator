package com.github.komidawi.pizzacostcalculator.data.db

import com.github.komidawi.pizzacostcalculator.data.model.PizzaModel

object SampleRepository : Repository {
    val pizzaList = mutableListOf<PizzaModel>()

    override fun add(pizza: PizzaModel) {
        pizzaList.add(pizza)
    }
}