package com.github.komidawi.pizzacostcalculator.screen.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.komidawi.pizzacostcalculator.data.repository.PizzaRepository
import com.github.komidawi.pizzacostcalculator.screen.add.AddPizzaFragmentViewModel
import com.github.komidawi.pizzacostcalculator.screen.list.PizzaListFragmentViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
@Suppress("UNCHECKED_CAST")
class TestViewModelFactory(private val pizzaRepository: PizzaRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(AddPizzaFragmentViewModel::class.java) ->
                    AddPizzaFragmentViewModel(pizzaRepository, TestCoroutineDispatcher())
                isAssignableFrom(PizzaListFragmentViewModel::class.java) ->
                    PizzaListFragmentViewModel(pizzaRepository, TestCoroutineDispatcher())
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}