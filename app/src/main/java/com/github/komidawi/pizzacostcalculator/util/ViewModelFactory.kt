package com.github.komidawi.pizzacostcalculator.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.komidawi.pizzacostcalculator.add.AddPizzaFragmentViewModel

@Suppress("UNCHECKED_CAST")
object ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(AddPizzaFragmentViewModel::class.java) ->
                    AddPizzaFragmentViewModel(mutableListOf())
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}