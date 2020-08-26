package com.github.komidawi.pizzacostcalculator.screen.list

import androidx.lifecycle.ViewModel
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabaseDao
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