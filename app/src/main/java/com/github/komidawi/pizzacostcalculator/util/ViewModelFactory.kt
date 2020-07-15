package com.github.komidawi.pizzacostcalculator.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.komidawi.pizzacostcalculator.add.AddPizzaFragmentViewModel
import com.github.komidawi.pizzacostcalculator.data.Repository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(AddPizzaFragmentViewModel::class.java) ->
                    AddPizzaFragmentViewModel(repository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}