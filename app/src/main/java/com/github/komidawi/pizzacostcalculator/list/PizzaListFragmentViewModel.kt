package com.github.komidawi.pizzacostcalculator.list

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.komidawi.pizzacostcalculator.database.PizzaDatabaseDao
import com.github.komidawi.pizzacostcalculator.database.PizzaEntity
import kotlinx.coroutines.*

class PizzaListFragmentViewModel(private val pizzaDatabaseDao: PizzaDatabaseDao) : ViewModel() {

    private var viewModelJob = Job()

    /**
     * A [CoroutineScope] keeps track of all coroutines started by this ViewModel.
     *
     * Because we pass it [viewModelJob], any coroutine started in this uiScope can be cancelled
     * by calling `viewModelJob.cancel()`
     *
     * By default, all coroutines started in uiScope will launch in [Dispatchers.Main] which is
     * the main thread on Android. This is a sensible default because most coroutines started by
     * a [ViewModel] update the UI after performing some processing.
     */
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val pizzaList = pizzaDatabaseDao.getAll()

    val pizzaListText = Transformations.map(pizzaList) { pizzas ->
        pizzas.toString()
    }

    fun addPizza(pizza: PizzaEntity) {
        uiScope.launch {
            add(pizza)
        }
    }

    private suspend fun add(pizza: PizzaEntity) {
        withContext(Dispatchers.IO) {
            pizzaDatabaseDao.insert(pizza)
        }
    }

    fun onClear() {
        uiScope.launch {
            clear()
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            pizzaDatabaseDao.deleteAll()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}