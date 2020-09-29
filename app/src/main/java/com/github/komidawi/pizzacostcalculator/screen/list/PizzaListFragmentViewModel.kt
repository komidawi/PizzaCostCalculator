package com.github.komidawi.pizzacostcalculator.screen.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.komidawi.pizzacostcalculator.data.db.PizzaDatabaseDao
import com.github.komidawi.pizzacostcalculator.data.db.PizzaEntity
import com.github.komidawi.pizzacostcalculator.network.RestApi
import kotlinx.coroutines.*

class PizzaListFragmentViewModel(private val pizzaDatabaseDao: PizzaDatabaseDao) : ViewModel() {

    private val _displayFetchingStatusToast = MutableLiveData(false)
    val displayFetchingStatusToast: LiveData<Boolean>
        get() = _displayFetchingStatusToast

    private val _fetchingStatusMessage = MutableLiveData<String>()
    val fetchingStatusMessage: LiveData<String>
        get() = _fetchingStatusMessage

    val pizzaList = pizzaDatabaseDao.getAll()

    init {
        fetchAllPizzas()
    }

    private fun fetchAllPizzas() {
        viewModelScope.launch {
            try {
                val allPizzas = RestApi.retrofitService.getAllPizzas()
                _fetchingStatusMessage.value = "Success, ${allPizzas.size} fetched"
            } catch (e: Exception) {
                _fetchingStatusMessage.value = "Error while fetching pizzas: ${e.message}"
            }
            _displayFetchingStatusToast.value = true
        }
    }

    fun onRemove(pizza: PizzaEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                pizzaDatabaseDao.deleteById(pizza.id)
            }
        }
    }

    fun onClear() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                pizzaDatabaseDao.deleteAll()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}