package com.github.komidawi.pizzacostcalculator.list

import androidx.lifecycle.ViewModel
import com.github.komidawi.pizzacostcalculator.model.PizzaModel

class PizzaListFragmentViewModel : ViewModel() {
    private val pizzaList = mutableListOf<PizzaModel>()

    fun addPizza(pizza: PizzaModel) {
        pizzaList.add(pizza)
    }
}