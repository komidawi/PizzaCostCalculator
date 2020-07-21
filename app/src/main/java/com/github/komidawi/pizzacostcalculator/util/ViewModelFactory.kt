package com.github.komidawi.pizzacostcalculator.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.komidawi.pizzacostcalculator.add.AddPizzaFragmentViewModel
import com.github.komidawi.pizzacostcalculator.database.PizzaDatabaseDao
import com.github.komidawi.pizzacostcalculator.database.Repository
import com.github.komidawi.pizzacostcalculator.list.PizzaListFragmentViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val databaseDao: PizzaDatabaseDao) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(AddPizzaFragmentViewModel::class.java) ->
                    AddPizzaFragmentViewModel()
                isAssignableFrom(PizzaListFragmentViewModel::class.java) ->
                    PizzaListFragmentViewModel(databaseDao)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}