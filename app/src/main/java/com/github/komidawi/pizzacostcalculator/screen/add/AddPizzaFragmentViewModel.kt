package com.github.komidawi.pizzacostcalculator.screen.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabaseDao
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import kotlinx.coroutines.*

class AddPizzaFragmentViewModel(private val pizzaDatabaseDao: PizzaDatabaseDao) : ViewModel() {

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val name = MutableLiveData<String>()

    val size = MutableLiveData<String>()

    val price = MutableLiveData<String>()

    private val _navigateToPizzaListFragment = MutableLiveData<Boolean>()
    val navigateToPizzaListFragment: LiveData<Boolean>
        get() = _navigateToPizzaListFragment


    fun handleAddPizza() {
        val pizza = createPizza()
        pizza?.let {
            uiScope.launch {
                insertPizza(it)
                _navigateToPizzaListFragment.value = true
            }
        }
    }

    private suspend fun insertPizza(pizza: PizzaEntity) {
        withContext(Dispatchers.IO) {
            pizzaDatabaseDao.insert(pizza)
        }
    }

    fun doneNavigating() {
        _navigateToPizzaListFragment.value = false
    }

    private fun createPizza(): PizzaEntity? {
        val currentName = name.value
        val currentSize = size.value
        val currentPrice = price.value

        return if (currentName == null || currentSize == null || currentPrice == null) {
            null
        } else {
            PizzaEntity(0L, currentName, currentSize.toFloat(), currentPrice.toFloat())
        }
    }
}