package com.github.komidawi.pizzacostcalculator.screen.list

import androidx.lifecycle.ViewModel
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabaseDao
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import kotlinx.coroutines.*

class PizzaListFragmentViewModel(private val pizzaDatabaseDao: PizzaDatabaseDao) : ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val pizzaList = pizzaDatabaseDao.getAll()


    fun onRemove(pizza: PizzaEntity) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                pizzaDatabaseDao.deleteById(pizza.id)
            }
        }
    }

    fun onClear() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                pizzaDatabaseDao.deleteAll()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}