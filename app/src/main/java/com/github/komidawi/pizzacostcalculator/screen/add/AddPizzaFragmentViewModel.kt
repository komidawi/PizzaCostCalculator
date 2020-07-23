package com.github.komidawi.pizzacostcalculator.screen.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.komidawi.pizzacostcalculator.data.model.PizzaModel

class AddPizzaFragmentViewModel() : ViewModel() {

    val name = MutableLiveData<String>()

    val size = MutableLiveData<String>()

    val price = MutableLiveData<String>()

    private val _addedPizza = MutableLiveData<PizzaModel>()
    val addedPizza: LiveData<PizzaModel>
        get() = _addedPizza


    fun onAddPizza() {
        _addedPizza.value = createPizza()
    }

    fun onAddPizzaComplete() {
        _addedPizza.value = null
    }

    private fun createPizza(): PizzaModel? {
        val currentName = name.value
        val currentSize = size.value
        val currentPrice = price.value

        return if (currentName == null || currentSize == null || currentPrice == null) {
            null
        } else {
            PizzaModel(currentName, currentSize.toFloat(), currentPrice.toFloat())
        }
    }
}