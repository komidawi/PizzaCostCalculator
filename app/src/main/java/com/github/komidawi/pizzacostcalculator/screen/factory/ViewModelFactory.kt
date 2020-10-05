package com.github.komidawi.pizzacostcalculator.screen.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.komidawi.pizzacostcalculator.data.PizzaRepository
import com.github.komidawi.pizzacostcalculator.screen.add.AddPizzaFragmentViewModel
import com.github.komidawi.pizzacostcalculator.screen.list.PizzaListFragmentViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val pizzaRepository: PizzaRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(AddPizzaFragmentViewModel::class.java) ->
                    AddPizzaFragmentViewModel(pizzaRepository)
                isAssignableFrom(PizzaListFragmentViewModel::class.java) ->
                    PizzaListFragmentViewModel(pizzaRepository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}