package com.github.komidawi.pizzacostcalculator.data

import com.github.komidawi.pizzacostcalculator.model.PizzaModel

object SampleRepository : Repository {
    val pizzaList = mutableListOf<PizzaModel>()

    override fun add(pizza: PizzaModel) {
        pizzaList.add(pizza)
    }
}