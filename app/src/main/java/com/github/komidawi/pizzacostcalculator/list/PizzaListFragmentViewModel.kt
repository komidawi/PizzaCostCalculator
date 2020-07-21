package com.github.komidawi.pizzacostcalculator.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.komidawi.pizzacostcalculator.database.PizzaDatabaseDao
import com.github.komidawi.pizzacostcalculator.model.PizzaModel

class PizzaListFragmentViewModel(val databaseDao: PizzaDatabaseDao) : ViewModel() {

    private val _pizzaList = MutableLiveData<MutableList<PizzaModel>>()
    val pizzaList: LiveData<MutableList<PizzaModel>>
        get() = _pizzaList

    val pizzaListText: LiveData<String> = Transformations.map(_pizzaList) { pizzaList ->
        pizzaList.toString()
    }

    init {
        _pizzaList.value = mutableListOf()
    }

    fun addPizza(pizza: PizzaModel) {
        _pizzaList.value?.add(pizza)
    }
}