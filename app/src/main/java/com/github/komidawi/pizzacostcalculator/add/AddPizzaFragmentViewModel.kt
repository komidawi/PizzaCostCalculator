package com.github.komidawi.pizzacostcalculator.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.komidawi.pizzacostcalculator.model.PizzaModel

class AddPizzaFragmentViewModel(private val pizzaList: MutableList<PizzaModel>) : ViewModel() {

    val name = MutableLiveData<String>()

    val size = MutableLiveData<String>()

    val price = MutableLiveData<String>()

    fun addPizza() {
        pizzaList.add(createPizza())
    }

    private fun createPizza(): PizzaModel {
        val currentName = name.value
        val currentSize = size.value
        val currentPrice = price.value

        if (currentName == null || currentSize == null || currentPrice == null) {
            throw IllegalArgumentException("Fields must be not empty!")
        } else {
            return PizzaModel(currentName, currentSize.toFloat(), currentPrice.toFloat())
        }
    }
}